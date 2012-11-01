package com.eprize.montana.common.model.promotion.time;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 
 * @author Tom.Myers
 *
 */
public class NonProductionModeTimekeeper extends AbstractTimekeeper {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private String timezoneOverride;
	
	/* */
	private long offsetInMillis;
	
	/* */
	private long pauseTimeInMillis;
	
	/* */
	private boolean pauseTime;
	
	/**
	 * 
	 */
	public NonProductionModeTimekeeper() {
		
	}

	/**
	 * 
	 * @param offsetInMillis
	 */
	public NonProductionModeTimekeeper(long offsetInMillis) {
		setOffsetInMillis(offsetInMillis);
	}
	
	/**
	 * @return the pauseTime
	 */
	public boolean isPauseTime() {
		return pauseTime;
	}

	/**
	 * @param pauseTime the pauseTime to set
	 */
	public void setPauseTime(boolean pauseTime) {
		if (pauseTime) {
			this.pauseTimeInMillis = 0;
		} else {
			this.pauseTimeInMillis = this.getCurrentTimeInMillis();	
		}
		this.pauseTime = pauseTime;
	}

	/**
	 * @return the timezoneOverride
	 */
	public String getTimezoneOverride() {
		return timezoneOverride;
	}

	/**
	 * @param timezoneOverride the timezoneOverride to set
	 */
	public void setTimezoneOverride(String timezoneOverride) {
		this.timezoneOverride = timezoneOverride;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getOffsetInMillis() {
		return this.offsetInMillis;
	}
	
	/**
	 * 
	 * @param offsetInMillis
	 */
	public void setOffsetInMillis(long offsetInMillis) {
		this.offsetInMillis = offsetInMillis;
	}
	
	/**
	 * 
	 */
	public void setOffsetForwardByOneDay() {
		this.offsetInMillis = this.offsetInMillis + NUM_MILLIS_IN_DAY;
	}

	/**
	 * 
	 */
	public void setOffsetBackwardByOneDay() {
		this.offsetInMillis = this.offsetInMillis - NUM_MILLIS_IN_DAY;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToLaunchDate()
	 */
	public void setTimeToLaunchDate() {
		Date date = this.getPromotion().getLaunchDate();
		setTimeToDate(date);
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToEndDate()
	 */
	public void setTimeToEndDate() {
		Date date = this.getPromotion().getEndDate();
		setTimeToDate(date);
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToArchiveDate()
	 */
	public void setTimeToArchiveDate() {
		Date date = this.getPromotion().getArchiveDate();
		setTimeToDate(date);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#setTimeToDate(java.sql.Date)
	 */
	public void setTimeToDate(Date date) {
		
		long dateMillis = date.getTime();
		long currentTimeMillis = this.getCurrentTimeInMillis();
		if (dateMillis > currentTimeMillis) {
			long delta = dateMillis - currentTimeMillis;
			this.offsetInMillis = this.offsetInMillis + delta;
		} else if (dateMillis < currentTimeMillis) {
			long delta = currentTimeMillis - dateMillis;
			this.offsetInMillis = this.offsetInMillis - delta;			
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getCurrentTimeInMillis()
	 */
	public long getCurrentTimeInMillis() {
		if (this.pauseTime) {
			return this.pauseTimeInMillis;
		}
		long currentTimeInMillis = System.currentTimeMillis();
		long adjustedTimeInMillis = currentTimeInMillis + this.offsetInMillis;
		return adjustedTimeInMillis;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getTimeZone()
	 */
	public TimeZone getTimeZone() {
		if (this.timezoneOverride != null && !this.timezoneOverride.isEmpty()) {
			return TimeZone.getTimeZone(this.timezoneOverride);
		} 		
		return TimeZone.getTimeZone(this.promotion.getTimezone());
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
		sb.append(": offsetInMillis=");
		sb.append(this.offsetInMillis);
		sb.append(": timezoneOverride=");
		sb.append(this.timezoneOverride);
		sb.append(": isPaused=");
		sb.append(this.pauseTime);
		sb.append(": currentTime: ");
		sb.append(new SimpleDateFormat().format(this.getCurrentDate()));
		return sb.toString();
	}
}