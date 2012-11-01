/**
 * 
 */
package com.eprize.montana.common.model.prize.awarding;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;


/**
 * 
 * @author tmyers
 * 
 */
public abstract class GamePlayResult extends DomainObject {
	
	/** */
	public static final String WIN = "WIN";
	
	/** */
	public static final String LOSE = "LOSE";

	
	/* */
	private static final long serialVersionUID = 1L;
	
	
	/* */
	private Long gamePlayResultId;
	
	/* */
	private GamePlayEntry gamePlayEntry;
	
	/* */
	private Date resultDate;

	/**
	 * 
	 * @return
	 */
	public abstract String getResult();
	
	/**
	 * @return the gamePlayResultId
	 */
	public Long getGamePlayResultId() {
		return gamePlayResultId;
	}

	/**
	 * @param gamePlayResultId the gamePlayResultId to set
	 */
	public void setGamePlayResultId(Long gamePlayResultId) {
		this.gamePlayResultId = gamePlayResultId;
	}

	/**
	 * @return the gamePlayEntry
	 */
	public GamePlayEntry getGamePlayEntry() {
		return gamePlayEntry;
	}

	/**
	 * @param gamePlayEntry the gamePlayEntry to set
	 */
	public void setGamePlayEntry(GamePlayEntry gamePlayEntry) {
		this.gamePlayEntry = gamePlayEntry;
	}

	/**
	 * @return the resultDate
	 */
	public Date getResultDate() {
		return resultDate;
	}

	/**
	 * @param resultDate the resultDate to set
	 */
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	
    /*
     * (non-Javadoc)
     * @see com.eprize.montana.common.model.IDomainObject#getPersistentIdentity()
     */
	public Long getPersistentIdentity() {
		return this.gamePlayResultId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(this.getGamePlayEntry().getNaturalIdentity());
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN).format(this.getResultDate()));
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(this.getResult());
		return sb.toString();
	}
}