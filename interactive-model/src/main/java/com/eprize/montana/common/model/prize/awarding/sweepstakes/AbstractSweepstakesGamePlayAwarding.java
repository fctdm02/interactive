/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding.sweepstakes;

import com.eprize.montana.common.model.prize.awarding.AbstractGamePlayPrizeAwarding;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizePool;


/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractSweepstakesGamePlayAwarding extends AbstractGamePlayPrizeAwarding {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @return the prizePool
	 */
	public SweepstakesPrizePool getSweepstakesPrizePool() {
		return (SweepstakesPrizePool)this.prizePool;
	}
}