package com.eprize.montana.common.model.promotion.time;

import java.util.TimeZone;

/**
 * 
 * @author Tom.Myers
 *
 */
public class ProductionModeTimekeeper extends AbstractTimekeeper {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public ProductionModeTimekeeper() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getCurrentTimeInMillis()
	 */
	public long getCurrentTimeInMillis() {
		return System.currentTimeMillis();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.promotion.time.ITimekeeper#getTimeZone()
	 */
	public TimeZone getTimeZone() {
		return TimeZone.getTimeZone(this.promotion.getTimezone());
	}
}