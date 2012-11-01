/**
 * 
 */
package com.eprize.montana.common.model.location;

import java.util.Set;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObject;

/**
 * 
 * @author tmyers
 * 
 */
public class Country extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long countryId;
	
	/* */
	private String countryCode;
	
	/* */
	private String countryName;
	
	/* */
	private String countryAbbreviation;
		
	/* */
	private Set<StateOrProvince> stateOrProvinces = new TreeSet<StateOrProvince>();
	
	/**
	 * 
	 */
	public Country() {
		
	}
	
	/**
	 * @return the countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the countryAbbreviation
	 */
	public String getCountryAbbreviation() {
		return countryAbbreviation;
	}

	/**
	 * @param countryAbbreviation the countryAbbreviation to set
	 */
	public void setCountryAbbreviation(String countryAbbreviation) {
		this.countryAbbreviation = countryAbbreviation;
	}

	/**
	 * @return the stateOrProvinces
	 */
	public Set<StateOrProvince> getStateOrProvinces() {
		return stateOrProvinces;
	}

	/**
	 * @param stateOrProvinces the stateOrProvinces to set
	 */
	public void setStateOrProvinces(Set<StateOrProvince> stateOrProvinces) {
		this.stateOrProvinces = stateOrProvinces;
	}

	/**
	 * 
	 * @param stateOrProvince
	 * @return
	 */
	public boolean addStateOrProvince(StateOrProvince stateOrProvince) {
		return this.stateOrProvinces.add(stateOrProvince);
	}
	
	/**
	 * 
	 * @param stateOrProvince
	 * @return
	 */
	public boolean removeStateOrProvince(StateOrProvince stateOrProvince) {
		return this.stateOrProvinces.remove(stateOrProvince);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.countryId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.countryCode);
		return sb.toString();
	}
}