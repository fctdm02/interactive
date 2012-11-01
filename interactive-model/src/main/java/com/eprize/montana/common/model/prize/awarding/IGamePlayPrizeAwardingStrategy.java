/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding;

import java.util.Set;

import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;


/**
 * 
 * @author tmyers
 * 
 */
public interface IGamePlayPrizeAwardingStrategy {
	
	/**
	 * 
	 * @param prizePool
	 * @return Set<GamePlayResult>
	 */
	Set<GamePlayResult> performAwarding(AbstractPrizePool prizePool);
}