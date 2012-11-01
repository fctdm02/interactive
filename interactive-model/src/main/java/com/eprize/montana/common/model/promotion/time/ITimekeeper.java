package com.eprize.montana.common.model.promotion.time;

import java.sql.Date;
import java.util.TimeZone;

import com.eprize.montana.common.model.promotion.Promotion;

/**
 * 
 * @author Tom.Myers
 *
 */
public interface ITimekeeper {

	/** */
	long NUM_MILLIS_IN_HOUR = 3600000;
	
	/** */
	long NUM_MILLIS_IN_DAY = NUM_MILLIS_IN_HOUR * 24;

	/** */
	long NUM_MILLIS_IN_WEEK = NUM_MILLIS_IN_DAY * 7;
	
	/**
	 * 
	 * @return
	 */
	long getCurrentTimeInMillis();

	/**
	 * 
	 * @return
	 */
	java.sql.Date getCurrentDate();
	
	/**
	 * 
	 * @return
	 */
	TimeZone getTimeZone();
	
	
	// The following methods are not implemented in the production mode implementation and will throw an UnsupportedOperationException.
	/**
	 * @return the pauseTime
	 */
	boolean isPauseTime();

	/**
	 * @param pauseTime the pauseTime to set
	 */
	void setPauseTime(boolean pauseTime);

	/**
	 * @return the timezoneOverride
	 */
	String getTimezoneOverride();

	/**
	 * @param timezoneOverride the timezoneOverride to set
	 */
	void setTimezoneOverride(String timezoneOverride);
	
	/**
	 * 
	 * @return
	 */
	long getOffsetInMillis();
	
	/**
	 * 
	 * @param offsetInMillis
	 */
	void setOffsetInMillis(long offsetInMillis);
	
	/**
	 * 
	 */
	void setOffsetForwardByOneDay();

	/**
	 * 
	 */
	void setOffsetBackwardByOneDay();
	
	/**
	 * 
	 */
	void setTimeToLaunchDate();

	/**
	 * 
	 */
	void setTimeToEndDate();

	/**
	 * 
	 */
	void setTimeToArchiveDate();
	
	/**
	 * 
	 * @param date
	 */
	void setTimeToDate(Date date);
	
	/**
	 * 
	 * @return
	 */
	Promotion getPromotion();

	/**
	 * 
	 * @param promotion
	 */
	void setPromotion(Promotion promotion);
	
	/**
	 * @param fromDate
	 * @param numberOfDays
	 * @return A new Date instance that is <code>numberOfDays</code> days in advance of <code>fromDate</code>
	 */
	java.sql.Date getDateFromDatePlusNumberOfDays(java.sql.Date fromDate, int numberOfDays);
	
}