/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding.sweepstakes;

import java.sql.Date;
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
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizeDrawing;
import com.eprize.montana.common.model.prize.pool.sweepstakes.SweepstakesPrizePool;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.promotion.Promotion;
import com.eprize.montana.common.model.user.Registrant;

/**
 * 
 * @author tmyers
 * 
 */
public class ScheduledSweepstakesDrawingAwarding extends AbstractSweepstakesGamePlayAwarding {

	/* */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.prize.awarding.AbstractGamePlayPrizeAwarding#performPrizeAwarding()
	 */
	public void performPrizeAwarding() {

		// This set holds all the winners for the given prize pool (as there may be multiple drawings that occur).
		Set<WinningGamePlayResult> winningGamePlayResults = new HashSet<WinningGamePlayResult>();

		// First of all, ensure that the parent promotion has indeed concluded.
		AbstractPrizedProduct abstractPrizedProduct = prizePool.getPrizedProduct();
		Promotion promotion = abstractPrizedProduct.getPromotion();
		Date promotionEndDate = promotion.getEndDate();
		Date currentDate = promotion.getTimekeeper().getCurrentDate();
		if (currentDate.before(promotionEndDate)) {
			throw new IllegalStateException("Cannot perform promotion conclusion automated drawing awarding when the current date: ["
				+ currentDate
				+ "] is before the promotion promotion end date: ["
				+ promotionEndDate 
				+ "] for promotion: ["
				+ promotion
				+ "].");
		}
		
		
		// See how many prize drawings there are to perform.
		Random random = new Random();
		SweepstakesPrizePool sweepstakesPrizePool = this.getSweepstakesPrizePool();
		Set<SweepstakesPrizeDrawing> sweepstakesPrizeDrawings = sweepstakesPrizePool.getSweepstakesPrizeDrawings();
		
		
		// For each sweepstakes prize drawing, look at its drawing date and perform a drawing if we are on the drawing date 
		// (and the drawing has not been performed already)
		Iterator<SweepstakesPrizeDrawing> iterator = sweepstakesPrizeDrawings.iterator();
		while (iterator.hasNext()) {
			
			SweepstakesPrizeDrawing sweepstakesPrizeDrawing = iterator.next();
			
			Date drawingDate = sweepstakesPrizeDrawing.getDrawingDate();
			boolean drawingDateEqualsCurrentDate = drawingDate.toString().equals(currentDate.toString());
			boolean sweepstakesDrawingHasNotBeenPerformedYet = !sweepstakesPrizeDrawing.getHasDrawingBeenPerformed();
			if (drawingDateEqualsCurrentDate && sweepstakesDrawingHasNotBeenPerformedYet) {
				
				
				// See how many prizes to award for this drawing.
				int numberOfPrizesToAwardForDrawing = sweepstakesPrizeDrawing.getNumberOfPrizesToAwardForDrawing(); 
				
								
				// Now, see how many game play entries (i.e. sweepstakes entries) that were submitted between this
				// drawings constrain start/end dates.
				Date constrainStartDate = sweepstakesPrizeDrawing.getConstrainStartDate();
				Date constrainEndDate = sweepstakesPrizeDrawing.getConstrainEndDate();
				
				List<GamePlayEntry> dateEligibleGamePlayEntries = new ArrayList<GamePlayEntry>();
				Set<GamePlayEntry> winningGamePlayEntries = new HashSet<GamePlayEntry>();
				
				Iterator<GamePlayEntry> allGamePlayEntriesIterator = abstractPrizedProduct.getGamePlayEntries().iterator();
				while (allGamePlayEntriesIterator.hasNext()) {
					
					GamePlayEntry gamePlayEntry = allGamePlayEntriesIterator.next();
					
					Date gamePlayEntryDate = gamePlayEntry.getEntryDate();
					
					if (gamePlayEntryDate.toString().equals(constrainStartDate.toString()) || (gamePlayEntryDate.after(constrainStartDate) && gamePlayEntryDate.before(constrainEndDate))) {
						
						dateEligibleGamePlayEntries.add(gamePlayEntry);
					}
				}

				
				// See how many eligible entries there are to draw winners from.
				int numberOfEligibleEntries = dateEligibleGamePlayEntries.size();
				if (numberOfEligibleEntries > 0) {

					// Pick N random numbers (from the size of eligible entries)
					// where N is the number of prizes to award for this drawing.
					for (int i=0; i < numberOfPrizesToAwardForDrawing; i++) {
					
						int randomIndexOfWinningRegistrant = random.nextInt(numberOfEligibleEntries);
						GamePlayEntry winningGamePlayEntry = dateEligibleGamePlayEntries.get(randomIndexOfWinningRegistrant);
						winningGamePlayEntries.add(winningGamePlayEntry);
					}
				}
				
				// Mark all eligbible entries as "played", regardless of winning/losing outcome.
				Iterator<GamePlayEntry> dateEligibleGamePlayEntriesIterator = dateEligibleGamePlayEntries.iterator();
				while (dateEligibleGamePlayEntriesIterator.hasNext()) {
					
					GamePlayEntry dateEligibleGamePlayEntry = dateEligibleGamePlayEntriesIterator.next(); 
					dateEligibleGamePlayEntry.setHasBeenPlayed(Boolean.TRUE);
				}
				
				
				// Honor any group/individual win limiting.
				AbstractPrize prize = sweepstakesPrizeDrawing.getPrize();
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
			}
		}
		
		promotion.getWinningGamePlayResults().addAll(winningGamePlayResults);
	}	
}