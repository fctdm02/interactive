package com.eprize.montana.common.model.promotion.time;

import java.sql.Date;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractTimekeeper extends DomainObject implements ITimekeeper {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long timekeeperId;
	
	/* */
	protected Promotion promotion;
	
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
	
	/**
	 * @return the timekeeperId
	 */
	public Long getTimekeeperId() {
		return timekeeperId;
	}

	/**
	 * @param timekeeperId the timekeeperId to set
	 */
	public void setTimekeeperId(Long timekeeperId) {
		this.timekeeperId = timekeeperId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getCurrentDate()
	 */
	public java.sql.Date getCurrentDate() {
		return new java.sql.Date(this.getCurrentTimeInMillis());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#isPauseTime()
	 */
	public boolean isPauseTime() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setPauseTime(boolean)
	 */
	public void setPauseTime(boolean pauseTime) {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getTimezoneOverride()
	 */
	public String getTimezoneOverride() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimezoneOverride(java.lang.String)
	 */
	public void setTimezoneOverride(String timezoneOverride) {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getOffsetInMillis()
	 */
	public long getOffsetInMillis() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setOffsetInMillis(long)
	 */
	public void setOffsetInMillis(long offsetInMillis) {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setOffsetForwardByOneDay()
	 */
	public void setOffsetForwardByOneDay() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setOffsetBackwardByOneDay()
	 */
	public void setOffsetBackwardByOneDay() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToLaunchDate()
	 */
	public void setTimeToLaunchDate() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToEndDate()
	 */
	public void setTimeToEndDate() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToArchiveDate()
	 */
	public void setTimeToArchiveDate() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToDate(java.sql.Date)
	 */
	public void setTimeToDate(Date date) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getDateFromDatePlusNumberOfDays(java.sql.Date, int)
	 */
	public java.sql.Date getDateFromDatePlusNumberOfDays(java.sql.Date fromDate, int numberOfDays) {
		
		long fromDateMillis = fromDate.getTime();
		long delta = ITimekeeper.NUM_MILLIS_IN_DAY * (long)numberOfDays;
		long toDateMillis = fromDateMillis + delta;
		java.sql.Date toDate = new java.sql.Date(toDateMillis);
		return toDate;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.timekeeperId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

		StringBuilder sb = new StringBuilder();
		sb.append(this.promotion.getNaturalIdentity());
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(this.getClass().getSimpleName());
		return sb.toString();
	}
}