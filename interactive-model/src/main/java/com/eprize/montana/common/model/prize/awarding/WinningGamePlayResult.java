/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding;

import java.sql.Date;

import com.eprize.montana.common.model.prize.AbstractPrize;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author tmyers
 * 
 */
public class WinningGamePlayResult extends GamePlayResult {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private AbstractPrize prize;
	
	/* */
	private java.sql.Date resultDate;
	
	/* */
	private Boolean isPendingApproval;
	
	/* */
	private Promotion promotion;
	
	/**
	 * 
	 */
	public WinningGamePlayResult() {
		
	}

	/**
	 * 
	 * @param gamePlayEntry
	 * @param prize
	 * @param resultDate
	 * @param prize
	 */
	public WinningGamePlayResult(
		GamePlayEntry gamePlayEntry,
		AbstractPrize prize,
		Date resultDate) {
		this.setGamePlayEntry(gamePlayEntry);
		this.setPrize(prize);
		if (prize.getAffidavitRequired().equals(Boolean.TRUE)) {
			this.setIsPendingApproval(Boolean.TRUE);
		}
		this.setResultDate(resultDate);
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
	 * @return the resultDate
	 */
	public java.sql.Date getResultDate() {
		return resultDate;
	}

	/**
	 * @param resultDate the resultDate to set
	 */
	public void setResultDate(java.sql.Date resultDate) {
		this.resultDate = resultDate;
	}

	/**
	 * @return the isPendingApproval
	 */
	public Boolean getIsPendingApproval() {
		return isPendingApproval;
	}

	/**
	 * @param isPendingApproval the isPendingApproval to set
	 */
	public void setIsPendingApproval(Boolean isPendingApproval) {
		this.isPendingApproval = isPendingApproval;
	}

	/**
	 * @return the promotion
	 */
	public Promotion getPromotion() {
		return promotion;
	}

	/**
	 * @param promotion the promotion to set
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.product.prized.awarding.GamePlayResult#getResult()
	 */
	public String getResult() {
		return WIN;
	}	
}