package com.eprize.montana.common.model.promotion.state;

import com.eprize.montana.common.model.DomainObject;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractPromotionState extends DomainObject {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long promotionStateId;
	
	/* */
	private String state;

	/**
	 *
	 */
	public AbstractPromotionState() {		
		
	}
	
	/**
	 * @param state
	 */
	public AbstractPromotionState(String state) {
		this.state = state;
	}
		
	/**
	 * @return the promotionStateId
	 */
	public Long getPromotionStateId() {
		return promotionStateId;
	}

	/**
	 * @param promotionStateId the promotionStateId to set
	 */
	public void setPromotionStateId(Long promotionStateId) {
		this.promotionStateId = promotionStateId;
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
		return this.promotionStateId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		return this.state;
	}
}