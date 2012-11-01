/**
 * 
 */
package com.eprize.montana.common.model.lookandfeel;

import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.lookandfeel.deliverychannel.AbstractDeliveryChannel;
import com.eprize.montana.common.model.lookandfeel.email.AbstractEmail;
import com.eprize.montana.common.model.lookandfeel.marketinggoal.AbstractMarketingGoal;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author tmyers
 * 
 */
public class PromotionLookAndFeel extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long promotionLookAndFeelId;

	/* */
	protected Set<AbstractEmail> emails = new TreeSet<AbstractEmail>();
	
	/* */
	protected Set<AbstractMarketingGoal> marketingGoals = new TreeSet<AbstractMarketingGoal>();

	/* */
	protected Set<AbstractDeliveryChannel> deliveryChannels = new TreeSet<AbstractDeliveryChannel>();
	
	/* */
	protected Promotion promotion;
	
	/**
	 * 
	 */
	public PromotionLookAndFeel() {
	}
	
	/**
	 * @param promotion
	 */
	public PromotionLookAndFeel(Promotion promotion) {
		this.setPromotion(promotion);
	}
	
	/**
	 * @return the promotionLookAndFeelId
	 */
	public Long getPromotionLookAndFeelId() {
		return promotionLookAndFeelId;
	}

	/**
	 * @param promotionLookAndFeelId the promotionLookAndFeelId to set
	 */
	public void setPromotionLookAndFeelId(Long promotionLookAndFeelId) {
		this.promotionLookAndFeelId = promotionLookAndFeelId;
	}
	
	/**
	 * @return the emails
	 */
	public Set<AbstractEmail> getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(Set<AbstractEmail> emails) {
		this.emails = emails;
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
	 * @return the marketingGoals
	 */
	public Set<AbstractMarketingGoal> getMarketingGoals() {
		return marketingGoals;
	}

	/**
	 * @param marketingGoals the marketingGoals to set
	 */
	public void setMarketingGoals(Set<AbstractMarketingGoal> marketingGoals) {
		this.marketingGoals = marketingGoals;
	}
	
	/**
	 * 
	 * @param marketingGoal
	 * @return
	 */
	public boolean addMarketingGoal(AbstractMarketingGoal marketingGoal) {
		return this.marketingGoals.add(marketingGoal);
	}
	
	/**
	 * 
	 * @param marketingGoal
	 * @return
	 */
	public boolean removeMarketingGoal(AbstractMarketingGoal marketingGoal) {
		return this.marketingGoals.remove(marketingGoal);
	}

	/**
	 * @return the deliveryChannels
	 */
	public Set<AbstractDeliveryChannel> getDeliveryChannels() {
		return deliveryChannels;
	}

	/**
	 * @param deliveryChannels the deliveryChannels to set
	 */
	public void setDeliveryChannels(Set<AbstractDeliveryChannel> deliveryChannels) {
		this.deliveryChannels = deliveryChannels;
	}

	/**
	 * 
	 * @param deliveryChannel
	 * @return
	 */
	public boolean addDeliveryChannel(AbstractDeliveryChannel deliveryChannel) {
		deliveryChannel.setPromotionLookAndFeel(this);
		return this.deliveryChannels.add(deliveryChannel);
	}
	
	/**
	 * 
	 * @param deliveryChannel
	 * @return
	 */
	public boolean removeDeliveryChannel(AbstractDeliveryChannel deliveryChannel) {
		deliveryChannel.setPromotionLookAndFeel(null);
		return this.deliveryChannels.remove(deliveryChannel);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		
	   return this.promotionLookAndFeelId;
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