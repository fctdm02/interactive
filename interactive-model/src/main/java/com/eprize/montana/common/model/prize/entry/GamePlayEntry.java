/**
 * 
 */
package com.eprize.montana.common.model.prize.entry;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;
import com.eprize.montana.common.model.registration.RegistrantVisit;

/**
 * 
 * @author tmyers
 * 
 */
public class GamePlayEntry extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long gamePlayEntryId;
	
	/* */
	private AbstractPrizedProduct prizedProduct;
	
	/* */
	private RegistrantVisit registrantVisit;
	
	/* TODO: This can be removed, as it is redundant to the registrant visit date. */
	private Date entryDate;
	
	/* */
	private Boolean hasBeenPlayed;

	/**
	 * @return the gamePlayEntryId
	 */
	public Long getGamePlayEntryId() {
		return gamePlayEntryId;
	}

	/**
	 * @param gamePlayEntryId the gamePlayEntryId to set
	 */
	public void setGamePlayEntryId(Long gamePlayEntryId) {
		this.gamePlayEntryId = gamePlayEntryId;
	}

	/**
	 * @return the prizedProduct
	 */
	public AbstractPrizedProduct getPrizedProduct() {
		return prizedProduct;
	}

	/**
	 * @param abstractPrizedProduct the prizedProduct to set
	 */
	public void setPrizedProduct(AbstractPrizedProduct abstractPrizedProduct) {
		this.prizedProduct = abstractPrizedProduct;
	}
	
	/**
	 * @return the registrantVisist
	 */
	public RegistrantVisit getRegistrantVisit() {
		return registrantVisit;
	}

	/**
	 * @param registrantVisit the registrantVisit to set
	 */
	public void setRegistrantVisit(RegistrantVisit registrantVisit) {
		registrantVisit.setGamePlayEntry(this);
		this.registrantVisit = registrantVisit;
	}

	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	/**
	 * @return the hasBeenPlayed
	 */
	public Boolean getHasBeenPlayed() {
		return hasBeenPlayed;
	}

	/**
	 * @param hasBeenPlayed the hasBeenPlayed to set
	 */
	public void setHasBeenPlayed(Boolean hasBeenPlayed) {
		this.hasBeenPlayed = hasBeenPlayed;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.gamePlayEntryId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(this.prizedProduct.getNaturalIdentity());
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(this.getClass().getSimpleName());
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(this.registrantVisit.getNaturalIdentity());
		sb.append(NATURAL_IDENTITY_DELIMITER);
		sb.append(new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN).format(this.entryDate));
		return sb.toString();
	}
}