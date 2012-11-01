/**
 * 
 */
package com.eprize.montana.common.model.registration;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;

/**
 * 
 * @author tmyers
 * 
 */
public class EmailAddress extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long emailAddressId;
	
	/* */
	private String originalEmailAddress;
	
	/* */
	private String normalizedEmailAddress;

	/**
	 * 
	 */
	public EmailAddress() {
		
	}
	
	/**
	 * @return the emailAddressId
	 */
	public Long getEmailAddressId() {
		return emailAddressId;
	}

	/**
	 * @param emailAddressId the emailAddressId to set
	 */
	public void setEmailAddressId(Long emailAddressId) {
		this.emailAddressId = emailAddressId;
	}

	/**
	 * @return the originalEmailAddress
	 */
	public String getOriginalEmailAddress() {
		return originalEmailAddress;
	}

	/**
	 * @param originalEmailAddress the originalEmailAddress to set
	 */
	public void setOriginalEmailAddress(String originalEmailAddress) {
		this.originalEmailAddress = originalEmailAddress;
	}

	/**
	 * @return the normalizedEmailAddress
	 */
	public String getNormalizedEmailAddress() {
		return normalizedEmailAddress;
	}

	/**
	 * @param normalizedEmailAddress the normalizedEmailAddress to set
	 */
	public void setNormalizedEmailAddress(String normalizedEmailAddress) {
		this.normalizedEmailAddress = normalizedEmailAddress;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.emailAddressId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		if (this.normalizedEmailAddress != null) {
			return this.originalEmailAddress + " " + this.normalizedEmailAddress;	
		}
		return this.originalEmailAddress;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		if (this.originalEmailAddress == null || this.originalEmailAddress.isEmpty()) {
			validationErrors.put("originalEmailAddress", "Cannot be empty");
		}
		
		return validationErrors;
	}
}