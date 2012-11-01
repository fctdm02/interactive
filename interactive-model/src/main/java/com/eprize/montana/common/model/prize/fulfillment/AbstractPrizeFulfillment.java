/**
 * 
 */
package com.eprize.montana.common.model.prize.fulfillment;

import java.util.Set;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.prize.awarding.WinningGamePlayResult;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;


/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractPrizeFulfillment extends DomainObject {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long prizeFulfillmentId;
		
	/* */
	private AbstractPrizePool prizePool;

	/**
	 * 
	 */
	public AbstractPrizeFulfillment() {
		
	}
	
	/**
	 * @return the prizeFulfillmentId
	 */
	public Long getPrizeFulfillmentId() {
		return prizeFulfillmentId;
	}

	/**
	 * @param prizeFulfillmentId the prizeFulfillmentId to set
	 */
	public void setPrizeFulfillmentId(Long prizeFulfillmentId) {
		this.prizeFulfillmentId = prizeFulfillmentId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return this.getClass().getSimpleName();
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
	
	/**
	 * TODO: Would the result be another wrapper object with fulfillment info (such as when the prize was fulfilled, delivery method, etc.?) 
	 * @param winningGamePlays
	 * @return
	 */
	public Set<WinningGamePlayResult> fulfillPrizing(Set<WinningGamePlayResult> winningGamePlays) {
		// TODO: TDM: Implement.
		throw new RuntimeException("Not implemented yet.");
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.prizeFulfillmentId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.prizePool.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.getName());
		return sb.toString();
	}
}