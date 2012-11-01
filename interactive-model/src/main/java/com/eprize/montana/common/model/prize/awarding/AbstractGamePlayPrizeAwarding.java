/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractGamePlayPrizeAwarding extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	
	/* */
	private Long gamePlayPrizeAwardingId;

	/* */
	protected AbstractPrizePool prizePool;
	
	/**
	 * 
	 */
	public AbstractGamePlayPrizeAwarding() {
		
	}

	/**
	 * 
	 */
	public abstract void performPrizeAwarding();
	
	/**
	 * @return the gamePlayPrizeAwardingId
	 */
	public Long getGamePlayPrizeAwardingId() {
		return gamePlayPrizeAwardingId;
	}

	/**
	 * @param gamePlayPrizeAwardingId the gamePlayPrizeAwardingId to set
	 */
	public void setGamePlayPrizeAwardingId(Long gamePlayPrizeAwardingId) {
		this.gamePlayPrizeAwardingId = gamePlayPrizeAwardingId;
	}

	/**
	 * @return the prizePool
	 */
	public AbstractPrizePool getPrizePool() {
		return prizePool;
	}

	/**
	 * @param prizePool the prizePool to set
	 */
	public void setPrizePool(AbstractPrizePool prizePool) {
		this.prizePool = prizePool;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.gamePlayPrizeAwardingId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.prizePool.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.getClass().getSimpleName());
		return sb.toString();
	}
}