/**
 * 
 */
package com.eprize.montana.common.model.prize;

import java.util.UUID;

import com.eprize.montana.common.model.DomainObject;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractPrize extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	
	/** */
	public static final String CURRENCY_USD = "USD";

	
	/** */
	public static final String TEN_NINE_NINE_PARTY_EPRIZE = "EPrize";
	
	/** */
	public static final String TEN_NINE_NINE_PARTY_CLIENT = "Client";

	
	/* */
	private Long prizeId;
	
	/* */
	private UUID prizeUUID;
	
	/* */
	private String name;
	
	/* */
	private String description;
	
	/* */
	private Integer quantity;
	
	/* */
	private Double arv;
	
	/* */
	private String arvCurrency = CURRENCY_USD;
	
	/* */
	private String tenNineNineParty = TEN_NINE_NINE_PARTY_EPRIZE;

	/* */
	private Boolean affidavitRequired;
	
	/* */
	private Boolean isGrandPrize;
	
	/* */
	private Boolean sendWinningEmail;
	
	/* */
	private Boolean allowDifferentPrizeTypeWins;
	
	/* */
	private Integer numberOfRepickRounds;
	
	/* */
	private Integer individualWinLimit = Integer.valueOf(1);
	
	/* Optional- used only when the promotion is set to use prize group win limiting.  */
	private PrizeGroup prizeGroup;
	
	/**
	 * 
	 */
	public AbstractPrize() {
	
		this.prizeUUID = UUID.randomUUID();
	}
	
	/**
	 * @return the prizeId
	 */
	public Long getPrizeId() {
		return prizeId;
	}

	/**
	 * @param prizeId the prizeId to set
	 */
	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}

	/**
	 * @return the prizeUUID
	 */
	public UUID getPrizeUUID() {
		return prizeUUID;
	}

	/**
	 * @param prizeUUID the prizeUUID to set
	 */
	public void setPrizeUUID(UUID prizeUUID) {
		this.prizeUUID = prizeUUID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the arv
	 */
	public Double getArv() {
		return arv;
	}

	/**
	 * @param arv the arv to set
	 */
	public void setArv(Double arv) {
		this.arv = arv;
	}

	/**
	 * @return the arvCurrency
	 */
	public String getArvCurrency() {
		return arvCurrency;
	}

	/**
	 * @param arvCurrency the arvCurrency to set
	 */
	public void setArvCurrency(String arvCurrency) {
		this.arvCurrency = arvCurrency;
	}

	/**
	 * @return the tenNineNineParty
	 */
	public String getTenNineNineParty() {
		return tenNineNineParty;
	}

	/**
	 * @param tenNineNineParty the tenNineNineParty to set
	 */
	public void setTenNineNineParty(String tenNineNineParty) {
		this.tenNineNineParty = tenNineNineParty;
	}

	/**
	 * @return the affidavitRequired
	 */
	public Boolean getAffidavitRequired() {
		return affidavitRequired;
	}

	/**
	 * @param affidavitRequired the affidavitRequired to set
	 */
	public void setAffidavitRequired(Boolean affidavitRequired) {
		this.affidavitRequired = affidavitRequired;
	}

	/**
	 * @return the isGrandPrize
	 */
	public Boolean getIsGrandPrize() {
		return isGrandPrize;
	}

	/**
	 * @param isGrandPrize the isGrandPrize to set
	 */
	public void setIsGrandPrize(Boolean isGrandPrize) {
		this.isGrandPrize = isGrandPrize;
	}

	/**
	 * @return the sendWinningEmail
	 */
	public Boolean getSendWinningEmail() {
		return sendWinningEmail;
	}

	/**
	 * @param sendWinningEmail the sendWinningEmail to set
	 */
	public void setSendWinningEmail(Boolean sendWinningEmail) {
		this.sendWinningEmail = sendWinningEmail;
	}

	/**
	 * @return the allowDifferentPrizeTypeWins
	 */
	public Boolean getAllowDifferentPrizeTypeWins() {
		return allowDifferentPrizeTypeWins;
	}

	/**
	 * @param allowDifferentPrizeTypeWins the allowDifferentPrizeTypeWins to set
	 */
	public void setAllowDifferentPrizeTypeWins(Boolean allowDifferentPrizeTypeWins) {
		this.allowDifferentPrizeTypeWins = allowDifferentPrizeTypeWins;
	}

	/**
	 * @return the numberOfRepickRounds
	 */
	public Integer getNumberOfRepickRounds() {
		return numberOfRepickRounds;
	}

	/**
	 * @param numberOfRepickRounds the numberOfRepickRounds to set
	 */
	public void setNumberOfRepickRounds(Integer numberOfRepickRounds) {
		this.numberOfRepickRounds = numberOfRepickRounds;
	}

	/**
	 * @return the individualWinLimit
	 */
	public Integer getIndividualWinLimit() {
		return individualWinLimit;
	}

	/**
	 * @param individualWinLimit the individualWinLimit to set
	 */
	public void setIndividualWinLimit(Integer individualWinLimit) {
		this.individualWinLimit = individualWinLimit;
	}
	
	/**
	 * @return the prizeGroup
	 */
	public PrizeGroup getPrizeGroup() {
		return prizeGroup;
	}

	/**
	 * @param prizeGroup the prizeGroup to set
	 */
	public void setPrizeGroup(PrizeGroup prizeGroup) {
		this.prizeGroup = prizeGroup;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.getPrizeId();		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		return this.getPrizeUUID().toString();		
	}
}