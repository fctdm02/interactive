/**
 * 
 */
package com.eprize.montana.common.model.location;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;

/**
 * 
 * @author tmyers
 * 
 */
public class ZipOrPostalCode extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long zipOrPostalCodeId;
	
	/* */
	private String zipOrPostalCodeValue;
	
	/* */
	private StateOrProvince stateOrProvince;
	
	/**
	 * 
	 */
	public ZipOrPostalCode() {
		
	}
	
	/**
	 * @return the zipOrPostalCodeId
	 */
	public Long getZipOrPostalCodeId() {
		return zipOrPostalCodeId;
	}

	/**
	 * @param zipOrPostalCodeId the zipOrPostalCodeId to set
	 */
	public void setZipOrPostalCodeId(Long zipOrPostalCodeId) {
		this.zipOrPostalCodeId = zipOrPostalCodeId;
	}

	/**
	 * @return the zipOrPostalCodeValue
	 */
	public String getZipOrPostalCodeValue() {
		return zipOrPostalCodeValue;
	}

	/**
	 * @param zipOrPostalCodeValue the zipOrPostalCodeValue to set
	 */
	public void setZipOrPostalCodeValue(String zipOrPostalCodeValue) {
		this.zipOrPostalCodeValue = zipOrPostalCodeValue;
	}

	/**
	 * @return the stateOrProvince
	 */
	public StateOrProvince getStateOrProvince() {
		return stateOrProvince;
	}

	/**
	 * @param stateOrProvince the stateOrProvince to set
	 */
	public void setStateOrProvince(StateOrProvince stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.zipOrPostalCodeId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.stateOrProvince.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.zipOrPostalCodeValue);		
		return sb.toString();
	}
}