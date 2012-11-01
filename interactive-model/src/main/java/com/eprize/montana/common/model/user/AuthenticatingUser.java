/**
 * 
 */
package com.eprize.montana.common.model.user;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AuthenticatingUser extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;

	/** */
	public static final String PASSWORD_VALIDATION_REG_EX = "/(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z]).*$)/";
	
	/** */
	public static final int MINIMUM_USERNAME_LENGTH = 4;

	/** */
	public static final int MAXIMUM_USERNAME_LENGTH = 32;
	
	/* */
	private Long userId;
	
	/* */
	private String username;
	
	/* */
	private String encodedPassword;
	
	/**
	 * 
	 */
	public AuthenticatingUser() {
		
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the accountCoordinatorUsername
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param accountCoordinatorUsername the accountCoordinatorUsername to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the encodedPassword
	 */
	public String getEncodedPassword() {
		return encodedPassword;
	}

	/**
	 * @param encodedPassword the encodedPassword to set
	 */
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.getUserId();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		return this.getUsername();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.getUsername() == null) {
			validationErrors.put("accountCoordinatorUsername", "Cannot be null");
		}
		
		int length = this.getUsername().length();
		if (length < MINIMUM_USERNAME_LENGTH) {
			validationErrors.put("accountCoordinatorUsername", "Invalid length for accountCoordinatorUsername: [" + this.getUsername() + "], must be at least: [" + MINIMUM_USERNAME_LENGTH + "] chars, but was: [" + length + "] chars.");
		}

		if (length > MAXIMUM_USERNAME_LENGTH) {
			validationErrors.put("accountCoordinatorUsername", "Invalid length for accountCoordinatorUsername: [" + this.getUsername() + "], must be less or equal to: [" + MAXIMUM_USERNAME_LENGTH + "] chars, but was: [" + length + "] chars.");
		}
		
		if (this.getEncodedPassword() == null || this.getEncodedPassword().isEmpty()) {
			validationErrors.put("encoded password", "Cannot be null or empty.");
		}
		
		return validationErrors;
	}
}