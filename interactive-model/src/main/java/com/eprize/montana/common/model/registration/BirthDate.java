/**
 * 
 */
package com.eprize.montana.common.model.registration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.exception.ValidationException;

/**
 * 
 * @author tmyers
 * 
 */
public class BirthDate extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long birthDateId;
	
	/* */
	private Integer birthMonth;
	
	/* */
	private Integer birthDay;

	/* */
	private Integer birthYear;
	
	/**
	 * 
	 */
	public BirthDate() {		
	}

	/**
	 * @return the birthDateId
	 */
	public Long getBirthDateId() {
		return birthDateId;
	}

	/**
	 * @param birthDateId the birthDateId to set
	 */
	public void setBirthDateId(Long birthDateId) {
		this.birthDateId = birthDateId;
	}

	/**
	 * @return the birthMonth
	 */
	public Integer getBirthMonth() {
		return birthMonth;
	}

	/**
	 * @param birthMonth the birthMonth to set
	 */
	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}

	/**
	 * @return the birthDay
	 */
	public Integer getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the birthYear
	 */
	public Integer getBirthYear() {
		return birthYear;
	}

	/**
	 * @param birthYear the birthYear to set
	 */
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.birthDateId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(this.birthMonth);
		sb.append("-");
		sb.append(this.birthDay);
		sb.append("-");
		sb.append(this.birthYear);
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {

		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.birthMonth == null) {
			validationErrors.put("birthMonth", "Cannot be null");
		}

		int iBirthMonth = this.birthMonth.intValue();
		if (iBirthMonth <= 0 || iBirthMonth >= 13) {
			validationErrors.put("birthMonth", "Must be between 1 and 12");
		}
		
		if (this.birthDay == null) {
			validationErrors.put("birthDay", "Cannot be null");
		}

		int iBirthDay = this.birthDay.intValue();
		if (iBirthDay <= 0 || iBirthDay >= 31) {
			validationErrors.put("birthDay", "Must be between 1 and 31");
		}
		
		if (this.birthYear == null) {
			validationErrors.put("birthYear", "Cannot be null");
		}
		
		int iBirthYear = this.birthYear.intValue();
		if (iBirthYear <= 1900 || iBirthYear >= 2100) {
			validationErrors.put("birthyear", "Must be between 1900 and 2100");
		}
		
		return validationErrors;
	}
	
	/**
	 * 
	 * @return
	 * @throws ValidationException
	 */
	public java.sql.Date getDate() throws ValidationException {
		
		Map<String, String> validationErrors = this.validate();
		if (validationErrors.size() > 0) {
			throw new ValidationException(validationErrors.toString());
		}
						
		String strBirthMonth = null;
		int iBirthMonth = this.birthMonth.intValue();
		if (iBirthMonth < 10) {
			strBirthMonth = "0" + this.birthMonth.toString();
		} else {
			strBirthMonth = this.birthMonth.toString();
		}

		String strBirthDay = null;
		int iBirthDay = this.birthDay.intValue();
		if (iBirthDay < 10) {
			strBirthDay = "0" + this.birthDay.toString();
		} else {
			strBirthDay = this.birthDay.toString();
		}
		
		String dateString = strBirthMonth + strBirthDay + this.birthYear.toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
		sdf.setLenient(false);
		
		try {
			return new java.sql.Date(sdf.parse(dateString).getTime());
		} catch (ParseException pe) {
			throw new ValidationException("Invalid birth date: " + this, pe);
		}		
	}
}