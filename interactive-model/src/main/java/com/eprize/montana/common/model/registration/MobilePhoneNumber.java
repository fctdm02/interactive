package com.eprize.montana.common.model.registration;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;

public class MobilePhoneNumber extends DomainObject {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	
	/** */
	public static final String MOBILE_PHONE_NUMBER_VALIDATION_REG_EX = "/(^(1[-.]?)?(\\()?[0-9]{3}(\\))?[-.]?[0-9]{3}[-.]?[0-9]{4}$)|(^$)/";

	
	/* */
	private Long mobilePhoneNumberId;
	
	/* */
	private String originalMobilePhoneNumber;
	
	/* */
	private String normalizedMobilePhoneNumber;
		
	/**
	 * 
	 */
	public MobilePhoneNumber() {
		
	}
	
	/**
	 * @return the mobilePhoneNumberId
	 */
	public Long getMobilePhoneNumberId() {
		return mobilePhoneNumberId;
	}

	/**
	 * @param mobilePhoneNumberId the mobilePhoneNumberId to set
	 */
	public void setMobilePhoneNumberId(Long mobilePhoneNumberId) {
		this.mobilePhoneNumberId = mobilePhoneNumberId;
	}

	/**
	 * @return the originalMobilePhoneNumber
	 */
	public String getOriginalMobilePhoneNumber() {
		return originalMobilePhoneNumber;
	}

	/**
	 * @param originalMobilePhoneNumber the originalMobilePhoneNumber to set
	 */
	public void setOriginalMobilePhoneNumber(String originalMobilePhoneNumber) {
		this.originalMobilePhoneNumber = originalMobilePhoneNumber;
	}

	/**
	 * @return the normalizedMobilePhoneNumber
	 */
	public String getNormalizedMobilePhoneNumber() {
		return normalizedMobilePhoneNumber;
	}

	/**
	 * @param normalizedMobilePhoneNumber the normalizedMobilePhoneNumber to set
	 */
	public void setNormalizedMobilePhoneNumber(String normalizedMobilePhoneNumber) {
		this.normalizedMobilePhoneNumber = normalizedMobilePhoneNumber;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.mobilePhoneNumberId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		return this.originalMobilePhoneNumber + " " + this.normalizedMobilePhoneNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		if (this.originalMobilePhoneNumber == null || this.originalMobilePhoneNumber.isEmpty()) {
			validationErrors.put("originalMobilePhoneNumber", "Cannot be empty");
		}
		
		return validationErrors;
	}
}