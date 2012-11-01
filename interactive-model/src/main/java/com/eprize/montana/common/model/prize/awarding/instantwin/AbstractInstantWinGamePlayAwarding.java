/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding.instantwin;

import com.eprize.montana.common.model.prize.awarding.AbstractGamePlayPrizeAwarding;
import com.eprize.montana.common.model.prize.pool.instantwin.InstantWinPrizePool;


/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractInstantWinGamePlayAwarding extends AbstractGamePlayPrizeAwarding {

	/* */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @return the prizePool
	 */
	public InstantWinPrizePool getInstantWinPrizePool() {
		return (InstantWinPrizePool)this.prizePool;
	}
}