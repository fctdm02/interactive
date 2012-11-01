/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding.instantwin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.eprize.montana.common.model.DomainObjectFactory;
import com.eprize.montana.common.model.prize.AbstractPrize;
import com.eprize.montana.common.model.prize.awarding.WinningGamePlayResult;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.prize.pool.instantwin.InstantWinPrizePool;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author tmyers
 * 
 */
public class OddsBasedInstantWinGamePlayAwarding extends AbstractInstantWinGamePlayAwarding {

	/** */
	public static final int DEFAULT_ODDS_ONE_OUT_OF_TWO_CHANCE = 2;
	
	/* */
	private static final long serialVersionUID = DEFAULT_ODDS_ONE_OUT_OF_TWO_CHANCE;

	/* The default odds are 1 out of 2 chance of winning (50/50) */
	protected int odds = 2; 

	/**
	 * 
	 */
	public OddsBasedInstantWinGamePlayAwarding() {
		
	}
	
	/**
	 * @return the odds
	 */
	public int getOdds() {
		return odds;
	}

	/**
	 * @param odds the odds to set
	 */
	public void setOdds(int odds) {
		this.odds = odds;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.prize.awarding.AbstractGamePlayPrizeAwarding#performPrizeAwarding()
	 */
	public void performPrizeAwarding() {

		
		Set<WinningGamePlayResult> winningGamePlayResults = new HashSet<WinningGamePlayResult>();
		
		InstantWinPrizePool instantWinPrizePool = this.getInstantWinPrizePool();
		
		AbstractPrizedProduct abstractPrizedProduct = instantWinPrizePool.getPrizedProduct();
		
		Promotion promotion = abstractPrizedProduct.getPromotion();
		
		// Ensure that there are prizes left to award.  If not, then there's nothing to be done.
		AbstractPrize prize = instantWinPrizePool.getPrize();
        int numberOfPrizesAwardedForPrize = promotion.getNumberOfPrizesAwardedForPrize(prize);
		int prizeQuantity = prize.getQuantity().intValue();
		int numberOfAvailablePrizesToAward = prizeQuantity - numberOfPrizesAwardedForPrize;
		if (numberOfAvailablePrizesToAward > 0) {

			java.sql.Date constrainStartDate = instantWinPrizePool.getConstrainStartDate();
			java.sql.Date constrainEndDate = instantWinPrizePool.getConstrainEndDate();

			
			// Now, see how many game play entries were submitted between the constrain start/end dates.
			List<GamePlayEntry> dateEligibleGamePlayEntries = new ArrayList<GamePlayEntry>();
			Set<GamePlayEntry> winningGamePlayEntries = new HashSet<GamePlayEntry>();
			
			Iterator<GamePlayEntry> allGamePlayEntriesIterator = abstractPrizedProduct.getGamePlayEntries().iterator();
			while (allGamePlayEntriesIterator.hasNext()) {

				GamePlayEntry gamePlayEntry = allGamePlayEntriesIterator.next();
				
				java.sql.Date gamePlayEntryDate = gamePlayEntry.getEntryDate();
				if (gamePlayEntryDate.toString().equals(constrainStartDate.toString()) || (gamePlayEntryDate.after(constrainStartDate) && gamePlayEntryDate.before(constrainEndDate))) {
					
					gamePlayEntry.setHasBeenPlayed(Boolean.TRUE);
					dateEligibleGamePlayEntries.add(gamePlayEntry);
				}
			}
			
			
			// For each eligible entry, perform the odds based awarding.
			Random random = new Random();
			Iterator<GamePlayEntry> eligibleGamePlayEntryIterator = dateEligibleGamePlayEntries.iterator();
			while (eligibleGamePlayEntryIterator.hasNext()) {
				
				GamePlayEntry gamePlayEntry = eligibleGamePlayEntryIterator.next();

				int randomNumberIndex = random.nextInt(this.odds);
				int randomNumberThrow = random.nextInt(this.odds);
				if (randomNumberIndex == randomNumberThrow) {
					
					winningGamePlayEntries.add(gamePlayEntry);
				}
			}

			
			// Honor any group/individual win limiting.
			int winLimit = promotion.getPrizeWinLimit(prize);
							
			Set<GamePlayEntry> winLimitVictims = new HashSet<GamePlayEntry>();
			Iterator<GamePlayEntry> winningGamePlayEntriesIterator = winningGamePlayEntries.iterator();
			while (winningGamePlayEntriesIterator.hasNext()) {
				
				GamePlayEntry winningGamePlayEntry = winningGamePlayEntriesIterator.next();
				
				int winCount = 0;
									
				Iterator<WinningGamePlayResult> winningGamePlayResultsIterator = promotion.getWinningGamePlayResults().iterator();
				while (winningGamePlayResultsIterator.hasNext()) {
					
					WinningGamePlayResult iteratedWinningGamePlayResult = winningGamePlayResultsIterator.next();
					
					AbstractPrize iteratedPrize = iteratedWinningGamePlayResult.getPrize();
					Registrant iteratedRegistrant = iteratedWinningGamePlayResult.getGamePlayEntry().getRegistrantVisit().getRegistrant(); 
					
					if (iteratedPrize.equals(prize) 
						&& iteratedRegistrant.equals(winningGamePlayEntry.getRegistrantVisit().getRegistrant())
						&& (prize.getPrizeGroup() == null || iteratedPrize.getPrizeGroup() == null || !prize.getPrizeGroup().equals(iteratedPrize.getPrizeGroup()))) {
						
						winCount = winCount + 1;
					}
				}
				if (winCount > winLimit) {
					
					winLimitVictims.add(winningGamePlayEntry);
				}
			}
			winningGamePlayEntries.removeAll(winLimitVictims);
			
			
			// Now, create winning game play results and add them to the parent promotion (we don't care about losing results)
			winningGamePlayEntriesIterator = winningGamePlayEntries.iterator();
			while (winningGamePlayEntriesIterator.hasNext()) {
				
				GamePlayEntry winningGamePlayEntry = winningGamePlayEntriesIterator.next();
				
				winningGamePlayResults.add(DomainObjectFactory.createWinningGamePlayResult(
					winningGamePlayEntry,
					prize, 
					promotion));
			}
			
			
			// Now, add the winners to the parent promotion (the prizes will be fulfilled depending upon the fulfillment frequency for the prize pool) 
			promotion.getWinningGamePlayResults().addAll(winningGamePlayResults);
		}
	}
}