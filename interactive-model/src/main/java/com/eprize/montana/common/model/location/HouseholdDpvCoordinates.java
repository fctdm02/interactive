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
public class HouseholdDpvCoordinates extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long householdDpvCoordinatesId;

	/* */
	private Double latitude;	

	/* */
	private Double longitude;
		
	/**
	 * 
	 */
	public HouseholdDpvCoordinates() {
		
	}
	
	/**
	 * @return the householdDpvCoordinatesId
	 */
	public Long getHouseholdDpvCoordinatesId() {
		return householdDpvCoordinatesId;
	}

	/**
	 * @param householdDpvCoordinatesId the householdDpvCoordinatesId to set
	 */
	public void setHouseholdDpvCoordinatesId(Long householdDpvCoordinatesId) {
		this.householdDpvCoordinatesId = householdDpvCoordinatesId;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.householdDpvCoordinatesId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.latitude);
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		sb.append(this.longitude);
		return sb.toString();
	}
}