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
 * 
-    ADDRESS_1 => "address1",
-    ADDRESS_2 => "address2",
-    LOCALITY_1 => "city",
-    REGION_1 => "state",
-    POST_CODE_1 => "zip",
-    POST_CODE_2 => "zip2",
-    CLATITUDE => "latitude",
-    CLONGITUDE => "longitude",
-
-    # address_type:
-    # BN: Building name
-    # F: Firm
-    # G: General delivery
-    # H: High-rise building
-    # L: Large Organization Postal Code (unique).
-    # M: Military
-    # O: Foreign
-    # P: Postal
-    # R: Rural
-    # S: Street
-    # X: Unknown, or the address was unassigned.
-    ADDRESS_TYPE => "iq8_address_type",
-
-    # dpv_status:
-    # Y - confirmed delivery point
-    # N - not a valid delivery point
-    # S - primary range valid, secondary (apt/suite) range not valid
-    # D - primary range valid, secondary range data not available
-    # blank - address verification failed
-    DPV_STATUS => "iq8_status",
-
-    # dpv_footnote:
-    # AA: Input address matches to the ZIP+4 file.
-    # A1: Input address does not match to the ZIP+4 file.
-    # BB: All input address components match to DPV.
-    # CC: Input address primary number matches to DPV, but the
-    #  secondary number does not match (the secondary is present
-    #  but invalid).
-    # M1: Input address primary number is missing.
-    # M3: Input address primary number is invalid.
-    # N1: Input address primary number matches to DPV but the
-    #  high-rise address is missing the secondary number.
-    # P1: Input address is missing the PO, RR, or HC Box number.
-    # P3: Input address is invalid PO, RR, or HC box number.
-    # RR: Input address matches to CMRA.
-    # R1: Input Address is DPV matched to CMRA, but PMB number
-    #  is not present.
-    DPV_FOOTNOTE => "iq8_footnote",
-
-    ADDR_FAULT_CODE => "iq8_fault_code"
 * 
 */
public class MailingAddress extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long mailingAddressId;
	
	/* */
	private String street1;
	
	/* */
	private String street2;

	/* */
	private String street3;
	
	/* */
	private String city;
	
	/* */
	private ZipOrPostalCode zipOrPostalCode;
	
	/**
	 * 
	 */
	public MailingAddress() {
		
	}

	/**
	 * @return the mailingAddressId
	 */
	public Long getMailingAddressId() {
		return mailingAddressId;
	}

	/**
	 * @param mailingAddressId the mailingAddressId to set
	 */
	public void setMailingAddressId(Long addressId) {
		this.mailingAddressId = addressId;
	}

	/**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * @param street1 the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * @return the street3
	 */
	public String getStreet3() {
		return street3;
	}

	/**
	 * @param street3 the street3 to set
	 */
	public void setStreet3(String street3) {
		this.street3 = street3;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zipOrPostalCode
	 */
	public ZipOrPostalCode getZipOrPostalCode() {
		return zipOrPostalCode;
	}

	/**
	 * @param zipOrPostalCode the zipOrPostalCode to set
	 */
	public void setZipOrPostalCode(ZipOrPostalCode zipOrPostalCode) {
		this.zipOrPostalCode = zipOrPostalCode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.mailingAddressId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.street1);
		sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		if (this.street2 != null) {
			sb.append(this.street2);
			sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
		}
		if (this.street3 != null) {
			sb.append(this.street3);
			sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);		
		}
		sb.append(this.city);
		if (this.zipOrPostalCode != null) {
			sb.append(IDomainObject.NATURAL_IDENTITY_DELIMITER);
			sb.append(this.zipOrPostalCode.getNaturalIdentity());
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMailingAddressAsString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.street1);
		sb.append(" ");
		if (this.street2 != null && !this.street2.isEmpty()) {
			sb.append(this.street2);
			sb.append(" ");
		}
		sb.append(this.city);
		sb.append(" ");
		sb.append(this.zipOrPostalCode.getStateOrProvince().getStateAbbreviation());
		sb.append(" ");
		sb.append(this.zipOrPostalCode.getZipOrPostalCodeValue());
		sb.append(" ");
		sb.append(this.zipOrPostalCode.getStateOrProvince().getCountry().getCountryAbbreviation());
		return sb.toString();
	}
}