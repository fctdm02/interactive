/**
 * 
 */
package com.eprize.montana.common.model.location;

import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;

/**
 * 
 * @author tmyers
 * 
 */
public class StateOrProvince extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long stateOrProvinceId;
	
	/* */
	private String stateName;
	
	/* */
	private String stateAbbreviation;
		
	/* */
	private Country country;
	
	/* */
	private Set<ZipOrPostalCode> zipOrPostalCodes = new TreeSet<ZipOrPostalCode>();
	
	/**
	 * 
	 */
	public StateOrProvince() {
		
	}
	
	/**
	 * @return the stateOrProvinceId
	 */
	public Long getStateOrProvinceId() {
		return stateOrProvinceId;
	}

	/**
	 * @param stateOrProvinceId the stateOrProvinceId to set
	 */
	public void setStateOrProvinceId(Long stateOrProvinceId) {
		this.stateOrProvinceId = stateOrProvinceId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the stateAbbreviation
	 */
	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	/**
	 * @param stateAbbreviation the stateAbbreviation to set
	 */
	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the zipOrPostalCodes
	 */
	public Set<ZipOrPostalCode> getZipOrPostalCodes() {
		return zipOrPostalCodes;
	}

	/**
	 * @param zipOrPostalCodes the zipOrPostalCodes to set
	 */
	public void setZipOrPostalCodes(Set<ZipOrPostalCode> zipOrPostalCodes) {
		this.zipOrPostalCodes = zipOrPostalCodes;
	}

	/**
	 * 
	 * @param zipOrPostalCode
	 * @return
	 */
	public boolean addZipOrPostalCode(ZipOrPostalCode zipOrPostalCode) {
		return this.zipOrPostalCodes.add(zipOrPostalCode);
	}
	
	/**
	 * 
	 * @param zipOrPostalCode
	 * @return
	 */
	public boolean removeZipOrPostalCode(ZipOrPostalCode zipOrPostalCode) {
		return this.zipOrPostalCodes.remove(zipOrPostalCode);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.stateOrProvinceId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.country.getNaturalIdentity());
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.stateName);		
		return sb.toString();
	}
}