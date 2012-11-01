/**
 * 
 */
package com.eprize.montana.common.model.prize.entry;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.location.MailingAddress;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.promotion.eligibility.PromotionEligibility;
import com.eprize.montana.common.model.user.Registrant;



/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractGamePlayEntryStrategy extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long gamePlayEntryStrategyId;

	/* */
	protected String name;

	/* */
	protected AbstractPrizedProduct prizedProduct;
	
	/**
	 * 
	 */
	public AbstractGamePlayEntryStrategy() {
		this.name = this.getClass().getSimpleName();
	}
	
	/**
	 * @return the gamePlayEntryStrategyId
	 */
	public Long getGamePlayEntryStrategyId() {
		return gamePlayEntryStrategyId;
	}

	/**
	 * @param gamePlayEntryStrategyId the gamePlayEntryStrategyId to set
	 */
	public void setGamePlayEntryStrategyId(Long gamePlayEntryStrategyId) {
		this.gamePlayEntryStrategyId = gamePlayEntryStrategyId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the prizedProduct
	 */
	public AbstractPrizedProduct getPrizedProduct() {
		return prizedProduct;
	}

	/**
	 * @param prizedProduct the prizedProduct to set
	 */
	public void setPrizedProduct(AbstractPrizedProduct prizedProduct) {
		this.prizedProduct = prizedProduct;
	}

	/**
	 * 
	 * 
	 * @param abstractPrizedProduct
	 * @param registrant
	 * @return
	 */
	public abstract Set<GamePlayEntry> getGamePlayEntry(AbstractPrizedProduct abstractPrizedProduct, Registrant registrant);
	
	/*
	 * TODO: TDM: Add logging.
	 * 
	 * @param prizedProduct
	 * @param registrant
	 * @return
	 */
	protected boolean isRegistrantEligible(AbstractPrizedProduct abstractPrizedProduct, Registrant registrant) {
		
		boolean isRegistrantEligible = true;
		
		Promotion promotion = abstractPrizedProduct.getPromotion();
		PromotionEligibility promotionEligibility = promotion.getPromotionEligibility();
		
		
		int registrantAge = registrant.getAge();
		if (registrantAge < promotionEligibility.getMinimumAge() || registrantAge > promotionEligibility.getMaximumAge()) {
			isRegistrantEligible = false;
		}
		
		
		// TODO: TDM: Each of these can be accomplished by adding convenience methods to the Promotion *or* in the service layer via queries.
		int maxRegistrationsPerEmail = promotionEligibility.getMaxRegistrationsPerEmail();
		int numberOfRegistrantsWithEmailAddress = promotion.getNumberOfRegistrantsWithEmailAddress(registrant.getEmailAddress()); 
		if (numberOfRegistrantsWithEmailAddress > maxRegistrationsPerEmail) {
			isRegistrantEligible = false;
		}
		
		
		int maxRegistrationsPerHousehold = promotionEligibility.getMaxRegistrationsPerHousehold();
		// Since we can't assume that every registrant has their mailing address filled, we can only perform this check if there is a mailing address.
		MailingAddress mailingAddress = registrant.getMailingAddress();
		if (mailingAddress != null) {
			
			int numberOfRegistrantsWithMailingAddress = promotion.getNumberOfRegistrantsWithMailingAddress(mailingAddress); 
			if (numberOfRegistrantsWithMailingAddress > maxRegistrationsPerHousehold) {
				isRegistrantEligible = false;
			}
		}
		
		
		int maxRegistrationsPerPerson = promotionEligibility.getMaxRegistrationsPerPerson();
		int getNumberOfRegistrantsWithGivenName = promotion.getNumberOfRegistrantsWithGivenName(registrant.getFirstName(), registrant.getLastName());
		if (getNumberOfRegistrantsWithGivenName > maxRegistrationsPerPerson) {
			isRegistrantEligible = false;
		}

		
		int maxRegistrationsPerIpAddress = promotionEligibility.getMaxRegistrationsPerIpAddress();
		Set<String> uniqueIpAddressesForRegistrantVisits = registrant.getUniqueIpAddressesForRegistrantVisits();
		Iterator<String> uniqueIpAddressForRegistrantVisitsIterator = uniqueIpAddressesForRegistrantVisits.iterator();
		while (uniqueIpAddressForRegistrantVisitsIterator.hasNext()) {
			
			String ipAddress = uniqueIpAddressForRegistrantVisitsIterator.next();
		
			int numberOfRegistrantsWithIpAddress = promotion.getNumberOfRegistrantsWithIpAddress(ipAddress); 
			if (numberOfRegistrantsWithIpAddress > maxRegistrationsPerIpAddress) {
				isRegistrantEligible = false;
				break;
			}
		}

						
		return isRegistrantEligible;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.gamePlayEntryStrategyId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

	   StringBuilder sb = new StringBuilder();
	   if (this.prizedProduct != null) {
		   sb.append(this.prizedProduct.getNaturalIdentity());
		   sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
	   }
	   sb.append(this.name);
	   return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();

		if (this.name == null || this.name.isEmpty()) {
			validationErrors.put("product name", "Cannot be empty");
		}
		
		return validationErrors;
	}
}