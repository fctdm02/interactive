package com.eprize.montana.common.model.lookandfeel.marketinggoal;

import com.eprize.montana.common.model.DomainObject;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractMarketingGoal extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long marketingGoalId;

	/* */
	protected String name;
	
	/**
	 * 
	 */
	public AbstractMarketingGoal() {
		
	}
	
	/**
	 * @return the marketingGoalId
	 */
	public Long getMarketingGoalId() {
		return marketingGoalId;
	}

	/**
	 * @param marketingGoalId the marketingGoalId to set
	 */
	public void setMarketingGoalId(Long marketingGoalId) {
		this.marketingGoalId = marketingGoalId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {
		return this.marketingGoalId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

		return this.name;
	}
}