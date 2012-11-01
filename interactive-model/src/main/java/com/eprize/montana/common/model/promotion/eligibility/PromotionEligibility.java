/**
 * 
 */
package com.eprize.montana.common.model.promotion.eligibility;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.promotion.eligibility.registrationfrequency.AbstractRegistrationFrequency;
import com.eprize.montana.common.model.promotion.eligibility.registrationfrequency.DailyRegistrationFrequency;

/**
 * 
 * @author tmyers
 * 
 */
public class PromotionEligibility extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long promotionEligiblityId;
	
	/* */
	private int minimumAge = 18;
	
	/* */
	private int maximumAge = 100;
	
	/* */
	private int maxRegistrationsPerPerson = 1;
	
	/* */
	private int maxRegistrationsPerEmail = 1;
	
	/* */
	private int maxRegistrationsPerHousehold = 5;
	
	/* */
	private int maxRegistrationsPerIpAddress = 100;
	
	/* */
	private Promotion promotion;
		
	/* */
	private AbstractRegistrationFrequency registrationFrequency = new DailyRegistrationFrequency();
	
	/**
	 * 
	 */
	public PromotionEligibility() {
		super();
		this.setRegistrationFrequency(new DailyRegistrationFrequency());
	}

	/**
	 * @param promotion
	 */
	public PromotionEligibility(Promotion promotion) {
		this();
		this.setPromotion(promotion);
	}
	
	/**
	 * @return the promotionEligiblityId
	 */
	public Long getPromotionEligiblityId() {
		return promotionEligiblityId;
	}

	/**
	 * @param promotionEligiblityId the promotionEligiblityId to set
	 */
	public void setPromotionEligiblityId(Long promotionEligiblityId) {
		this.promotionEligiblityId = promotionEligiblityId;
	}

	/**
	 * @return the minimumAge
	 */
	public int getMinimumAge() {
		return minimumAge;
	}

	/**
	 * @param minimumAge the minimumAge to set
	 */
	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	/**
	 * @return the maximumAge
	 */
	public int getMaximumAge() {
		return maximumAge;
	}

	/**
	 * @param maximumAge the maximumAge to set
	 */
	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	/**
	 * @return the maxRegistrationsPerPerson
	 */
	public int getMaxRegistrationsPerPerson() {
		return maxRegistrationsPerPerson;
	}

	/**
	 * @param maxRegistrationsPerPerson the maxRegistrationsPerPerson to set
	 */
	public void setMaxRegistrationsPerPerson(int maxRegistrationsPerPerson) {
		this.maxRegistrationsPerPerson = maxRegistrationsPerPerson;
	}

	/**
	 * @return the maxRegistrationsPerEmail
	 */
	public int getMaxRegistrationsPerEmail() {
		return maxRegistrationsPerEmail;
	}

	/**
	 * @param maxRegistrationsPerEmail the maxRegistrationsPerEmail to set
	 */
	public void setMaxRegistrationsPerEmail(int maxRegistrationsPerEmail) {
		this.maxRegistrationsPerEmail = maxRegistrationsPerEmail;
	}

	/**
	 * @return the maxRegistrationsPerHousehold
	 */
	public int getMaxRegistrationsPerHousehold() {
		return maxRegistrationsPerHousehold;
	}

	/**
	 * @param maxRegistrationsPerHousehold the maxRegistrationsPerHousehold to set
	 */
	public void setMaxRegistrationsPerHousehold(int maxRegistrationsPerHousehold) {
		this.maxRegistrationsPerHousehold = maxRegistrationsPerHousehold;
	}

	/**
	 * @return the maxRegistrationsPerIpAddress
	 */
	public int getMaxRegistrationsPerIpAddress() {
		return maxRegistrationsPerIpAddress;
	}

	/**
	 * @param maxRegistrationsPerIpAddress the maxRegistrationsPerIpAddress to set
	 */
	public void setMaxRegistrationsPerIpAddress(int maxRegistrationsPerIpAddress) {
		this.maxRegistrationsPerIpAddress = maxRegistrationsPerIpAddress;
	}
	
	/**
	 * 
	 * @return
	 */
	public Promotion getPromotion() {
		return this.promotion;
	}
	
	/**
	 * 
	 * @param promotion
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	/**
	 * @return the registrationFrequency
	 */
	public AbstractRegistrationFrequency getRegistrationFrequency() {
		return registrationFrequency;
	}

	/**
	 * @param registrationFrequency the registrationFrequency to set
	 */
	public void setRegistrationFrequency(AbstractRegistrationFrequency registrationFrequency) {
		registrationFrequency.setPromotionEligibility(this);
		this.registrationFrequency = registrationFrequency;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		
	   return this.promotionEligiblityId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
   public String getNaturalIdentity() {
	   
		StringBuilder sb = new StringBuilder();
		sb.append(this.promotion.getNaturalIdentity());
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(this.getClass().getSimpleName());
		return sb.toString();
   }
}