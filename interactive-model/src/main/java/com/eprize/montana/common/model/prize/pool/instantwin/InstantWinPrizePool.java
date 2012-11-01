/**
 * 
 */
package com.eprize.montana.common.model.prize.pool.instantwin;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.prize.AbstractPrize;
import com.eprize.montana.common.model.prize.pool.AbstractPrizePool;

/**
 * 
 * @author tmyers
 * 
 */
public class InstantWinPrizePool extends AbstractPrizePool {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	private java.sql.Date constrainStartDate;

	/* */
	private java.sql.Date constrainEndDate;
	
	/* */
	private AbstractPrize prize;
	
	/**
	 * 
	 */
	public InstantWinPrizePool() {
		
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
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.product.AbstractProduct#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.prize == null) {
			validationErrors.put("prize", "A prize must be specified for prize pool: " + this);
		}
		
		return validationErrors;
	}
}