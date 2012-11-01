/**
 * 
 */
package com.eprize.montana.common.model.prize.pool.sweepstakes;

import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.prize.AbstractPrize;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author tmyers
 * 
 */
public class SweepstakesPrizePool extends AbstractPrizePool {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	private Set<SweepstakesPrizeDrawing> sweepstakesPrizeDrawings = new TreeSet<SweepstakesPrizeDrawing>();
	
	/**
	 * 
	 */
	public SweepstakesPrizePool() {
		
	}

	/**
	 * @return the sweepstakesPrizeDrawings
	 */
	public Set<SweepstakesPrizeDrawing> getSweepstakesPrizeDrawings() {
		return sweepstakesPrizeDrawings;
	}

	/**
	 * @param sweepstakesPrizeDrawings the sweepstakesPrizeDrawings to set
	 */
	public void setSweepstakesPrizeDrawings(Set<SweepstakesPrizeDrawing> sweepstakesPrizeDrawings) {
		this.sweepstakesPrizeDrawings = sweepstakesPrizeDrawings;
	}
	
	/**
	 * 
	 * @param sweepstakesPrizeDrawing
	 * @return
	 */
	public boolean addSweepstakesPrizeDrawing(SweepstakesPrizeDrawing sweepstakesPrizeDrawing) {
		return this.sweepstakesPrizeDrawings.add(sweepstakesPrizeDrawing);
	}
	
	/**
	 * 
	 * @param sweepstakesPrizeDrawing
	 * @return
	 */
	public boolean removeSweepstakesPrizeDrawing(SweepstakesPrizeDrawing sweepstakesPrizeDrawing) {
		return this.sweepstakesPrizeDrawings.remove(sweepstakesPrizeDrawing);
	}
	
	/**
	 * @param prize the prize to create the sweepstakes drawing for
	 * 
	 * Creates a single sweepstakes prize drawing, for the given prize, held one day after the promotion 
	 * conclusion with the constrain start date being set to the promotion launch date and the constrain 
	 * end date being set to the promotion end date.
	 * </p>
	 * <b>IMPORTANT:</b> Any previously created sweepstakes prize drawings will be removed.
	 */
	public void createPromotionConclusionSweepstakesDrawing(AbstractPrize prize) {
		
		Promotion promotion = this.abstractPrizedProduct.getPromotion();
		java.sql.Date launchDate = promotion.getLaunchDate();
		java.sql.Date endDate = promotion.getEndDate();
		
		int numberOfDays = 1;
		java.sql.Date drawingDate = promotion.getTimekeeper().getDateFromDatePlusNumberOfDays(endDate, numberOfDays);
		
		SweepstakesPrizeDrawing sweepstakesPrizeDrawing = new SweepstakesPrizeDrawing();
		sweepstakesPrizeDrawing.setSweepstakesPrizePool(this);
		sweepstakesPrizeDrawing.setDrawingDate(drawingDate);
		sweepstakesPrizeDrawing.setConstrainStartDate(launchDate);
		sweepstakesPrizeDrawing.setConstrainEndDate(endDate);
		sweepstakesPrizeDrawing.setPrize(prize);
		sweepstakesPrizeDrawing.setHasDrawingBeenPerformed(Boolean.FALSE);
		
		this.sweepstakesPrizeDrawings.clear();
		this.addSweepstakesPrizeDrawing(sweepstakesPrizeDrawing);
	}
}