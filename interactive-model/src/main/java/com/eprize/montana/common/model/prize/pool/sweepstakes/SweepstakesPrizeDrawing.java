/**
 * 
 */
package com.eprize.montana.common.model.prize.pool.sweepstakes;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.prize.AbstractPrize;


/**
 * 
 * @author tmyers
 * 
 */
public class SweepstakesPrizeDrawing extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	private Long sweepstakesPrizeDrawingId;
		
	/* */
	private SweepstakesPrizePool sweepstakesPrizePool;

	/* */
	private java.sql.Date drawingDate;
	
	/* */
	private java.sql.Date constrainStartDate;

	/* */
	private java.sql.Date constrainEndDate;
	
	/* */
	private AbstractPrize prize;
	
	/* */
	private Boolean hasDrawingBeenPerformed = Boolean.FALSE;
	
	/**
	 * 
	 */
	public SweepstakesPrizeDrawing() {
		
	}

	/**
	 * @return the sweepstakesPrizeDrawingId
	 */
	public Long getSweepstakesPrizeDrawingId() {
		return sweepstakesPrizeDrawingId;
	}

	/**
	 * @param sweepstakesPrizeDrawingId the sweepstakesPrizeDrawingId to set
	 */
	public void setSweepstakesPrizeDrawingId(Long sweepstakesPrizeDrawingId) {
		this.sweepstakesPrizeDrawingId = sweepstakesPrizeDrawingId;
	}

	/**
	 * @return the prize
	 */
	public AbstractPrize getPrize() {
		return prize;
	}

	/**
	 * @param prize the prize to set
	 */
	public void setPrize(AbstractPrize prize) {
		this.prize = prize;
	}

	/**
	 * @return the sweepstakesPrizePool
	 */
	public SweepstakesPrizePool getSweepstakesPrizePool() {
		return sweepstakesPrizePool;
	}

	/**
	 * @param sweepstakesPrizePool the sweepstakesPrizePool to set
	 */
	public void setSweepstakesPrizePool(SweepstakesPrizePool sweepstakesPrizePool) {
		this.sweepstakesPrizePool = sweepstakesPrizePool;
	}

	/**
	 * @return the drawingDate
	 */
	public java.sql.Date getDrawingDate() {
		return drawingDate;
	}

	/**
	 * @param drawingDate the drawingDate to set
	 */
	public void setDrawingDate(java.sql.Date drawingDate) {
		this.drawingDate = drawingDate;
	}

	/**
	 * @return the constrainStartDate
	 */
	public java.sql.Date getConstrainStartDate() {
		return constrainStartDate;
	}

	/**
	 * @param constrainStartDate the constrainStartDate to set
	 */
	public void setConstrainStartDate(java.sql.Date constrainStartDate) {
		this.constrainStartDate = constrainStartDate;
	}

	/**
	 * @return the constrainEndDate
	 */
	public java.sql.Date getConstrainEndDate() {
		return constrainEndDate;
	}

	/**
	 * @param constrainEndDate the constrainEndDate to set
	 */
	public void setConstrainEndDate(java.sql.Date constrainEndDate) {
		this.constrainEndDate = constrainEndDate;
	}
	
	/**
	 * @return the hasDrawingBeenPerformed
	 */
	public Boolean getHasDrawingBeenPerformed() {
		return hasDrawingBeenPerformed;
	}

	/**
	 * @param hasDrawingBeenPerformed the hasDrawingBeenPerformed to set
	 */
	public void setHasDrawingBeenPerformed(Boolean hasDrawingBeenPerformed) {
		this.hasDrawingBeenPerformed = hasDrawingBeenPerformed;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.sweepstakesPrizeDrawingId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.sweepstakesPrizePool.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.drawingDate);
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.constrainStartDate);
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.constrainEndDate);
		return sb.toString();
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberOfPrizesToAwardForDrawing() {
		
		int numberOfDrawings = this.sweepstakesPrizePool.getSweepstakesPrizeDrawings().size();
		int totalNumberOfPrizesToAwardForPromotion = this.prize.getQuantity().intValue();
		
		// TODO: TDM: Deal with non even results (i.e. does the last drawing get the remainder?)
		
		int numberOfPrizesToAwardForDrawing = totalNumberOfPrizesToAwardForPromotion / numberOfDrawings;
		
		return numberOfPrizesToAwardForDrawing;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.product.AbstractProduct#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.prize == null) {
			validationErrors.put("prize", "A prize must be specified for sweepstakes prize drawing: " + this);
		}
		
		return validationErrors;
	}
}