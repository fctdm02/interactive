package com.eprize.montana.common.model.user.state;

import com.eprize.montana.common.model.DomainObject;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractRegistrantState extends DomainObject {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long registrantStateId;
	
	/* */
	private String state;

	/**
	 *
	 */
	public AbstractRegistrantState() {		
		
	}
	
	/**
	 * @param state
	 */
	public AbstractRegistrantState(String state) {
		this.state = state;
	}
		
	/**
	 * @return the registrantStateId
	 */
	public Long getRegistrantStateId() {
		return registrantStateId;
	}

	/**
	 * @param registrantStateId the registrantStateId to set
	 */
	public void setRegistrantStateId(Long registrantStateId) {
		this.registrantStateId = registrantStateId;
	}

	/**
	 * 
	 * @return
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.registrantStateId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		return this.state;
	}
}