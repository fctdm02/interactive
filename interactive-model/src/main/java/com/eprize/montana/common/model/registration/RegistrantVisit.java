/**
 * 
 */
package com.eprize.montana.common.model.registration;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.prize.entry.GamePlayEntry;
import com.eprize.montana.common.model.user.Registrant;


/**
 * 
 * @author tmyers
 * 
 */
public class RegistrantVisit extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long registrationVisitId;
	
	/* */
	private Date registrationDate;
	
	/* */
	private String ipAddress;
	
	/* */
	private String userAgent;

	/* */
	private Registrant registrant;
	
	/* */
	private GamePlayEntry gamePlayEntry;
	
	/* This is used to guarantee natural identity. (if more than one visit is created with the same registration date/user agent/ip address combination. */
	private Integer registrantVisitIndex;
	
	/**
	 * 
	 */
	public RegistrantVisit() {
		
	}

	/**
	 * @return the registrationVisitId
	 */
	public Long getRegistrationVisitId() {
		return registrationVisitId;
	}

	/**
	 * @param registrationVisitId the registrationVisitId to set
	 */
	public void setRegistrationVisitId(Long registrationVisitId) {
		this.registrationVisitId = registrationVisitId;
	}
	
	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the registrant
	 */
	public Registrant getRegistrant() {
		return registrant;
	}

	/**
	 * @param registrant the registrant to set
	 */
	public void setRegistrant(Registrant registrant) {
		this.registrant = registrant;
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
	 * @return the registrantVisitIndex
	 */
	public Integer getRegistrantVisitIndex() {
		return registrantVisitIndex;
	}

	/**
	 * @param registrantVisitIndex the registrantVisitIndex to set
	 */
	public void setRegistrantVisitIndex(Integer registrantVisitIndex) {
		this.registrantVisitIndex = registrantVisitIndex;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.registrationVisitId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN);
		StringBuilder sb = new StringBuilder();
		sb.append(this.registrant.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.ipAddress);
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.userAgent);
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(sdf.format(this.registrationDate));
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.registrantVisitIndex.toString());
		return sb.toString();
	}
} 