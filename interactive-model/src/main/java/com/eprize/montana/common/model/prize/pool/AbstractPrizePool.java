/**
 * 
 */
package com.eprize.montana.common.model.prize.pool;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.prize.awarding.AbstractGamePlayPrizeAwarding;
import com.eprize.montana.common.model.prize.fulfillment.AbstractPrizeFulfillment;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractPrizePool extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	
	/* */
	private Long prizePoolId;
	
	/* */
	protected UUID prizePoolUUID;
	
	/* */
	protected String name;
		
	/* */
	protected AbstractPrizeFulfillment prizeFulfillment;

	/* */
	protected AbstractGamePlayPrizeAwarding gamePlayPrizeAwarding;
	
	/* */
	protected AbstractPrizedProduct abstractPrizedProduct;

	/**
	 * 
	 */
	public AbstractPrizePool() {
		prizePoolUUID = UUID.randomUUID();
	}
	
	/**
	 * @return the prizePoolId
	 */
	public Long getPrizePoolId() {
		return prizePoolId;
	}

	/**
	 * @param prizePoolId the prizePoolId to set
	 */
	public void setPrizePoolId(Long prizePoolId) {
		this.prizePoolId = prizePoolId;
	}

	/**
	 * @return the prizePoolUUID
	 */
	public UUID getPrizePoolUUID() {
		return prizePoolUUID;
	}

	/**
	 * @param prizePoolUUID the prizePoolUUID to set
	 */
	public void setPrizePoolUUID(UUID prizePoolUUID) {
		this.prizePoolUUID = prizePoolUUID;
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
	 * @return the prizeFulfillment
	 */
	public AbstractPrizeFulfillment getPrizeFulfillment() {
		return prizeFulfillment;
	}

	/**
	 * @param prizeFulfillment the prizeFulfillment to set
	 */
	public void setPrizeFulfillment(AbstractPrizeFulfillment prizeFulfillment) {
		this.prizeFulfillment = prizeFulfillment;
	}
	
	/**
	 * @return the gamePlayPrizeAwarding
	 */
	public AbstractGamePlayPrizeAwarding getGamePlayPrizeAwarding() {
		return gamePlayPrizeAwarding;
	}

	/**
	 * @param gamePlayPrizeAwarding the gamePlayPrizeAwarding to set
	 */
	public void setGamePlayPrizeAwarding(AbstractGamePlayPrizeAwarding gamePlayPrizeAwarding) {
		gamePlayPrizeAwarding.setPrizePool(this);
		this.gamePlayPrizeAwarding = gamePlayPrizeAwarding;
	}

	/**
	 * @return the abstractPrizedProduct
	 */
	public AbstractPrizedProduct getPrizedProduct() {
		return abstractPrizedProduct;
	}

	/**
	 * @param abstractPrizedProduct the abstractPrizedProduct to set
	 */
	public void setPrizedProduct(AbstractPrizedProduct abstractPrizedProduct) {
		this.abstractPrizedProduct = abstractPrizedProduct;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.prizePoolId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.abstractPrizedProduct.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.name);
		return sb.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.product.AbstractProduct#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();

		if (this.name == null || this.name.isEmpty()) {
			validationErrors.put("name", "name must be specified for prize pool: " + this);
		}
		
		if (this.prizeFulfillment == null) {
			validationErrors.put("prizeFulfillment", "prizeFulfillment must be specified for prize pool: " + this);
		}

		if (this.gamePlayPrizeAwarding == null) {
			validationErrors.put("gamePlayPrizeAwarding", "gamePlayPrizeAwarding must be specified for prize pool: " + this);
		}

		if (this.abstractPrizedProduct == null) {
			validationErrors.put("abstractPrizedProduct", "abstractPrizedProduct must be specified for prize pool: " + this);
		}
		
		return validationErrors;
	}	
}