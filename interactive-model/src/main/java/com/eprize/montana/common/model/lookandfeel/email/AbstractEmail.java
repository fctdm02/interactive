package com.eprize.montana.common.model.lookandfeel.email;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.lookandfeel.PromotionLookAndFeel;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractEmail extends DomainObject {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long emailId;

	/* */
	protected String name;

	/* */
	protected String sender;
	
	/* */
	protected String subjectLine;
	
	/* */
	protected String body;
	
	/* */
	protected PromotionLookAndFeel promotionLookAndFeel;
	
	/**
	 * 
	 */
	public AbstractEmail() {
		
	}
	
	/**
	 * @return the emailId
	 */
	public Long getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
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

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the subjectLine
	 */
	public String getSubjectLine() {
		return subjectLine;
	}

	/**
	 * @param subjectLine the subjectLine to set
	 */
	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the promotionLookAndFeel
	 */
	public PromotionLookAndFeel getPromotionLookAndFeel() {
		return promotionLookAndFeel;
	}

	/**
	 * @param promotionLookAndFeel the promotionLookAndFeel to set
	 */
	public void setPromotionLookAndFeel(PromotionLookAndFeel promotionLookAndFeel) {
		this.promotionLookAndFeel = promotionLookAndFeel;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {

		return this.emailId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

		return this.name;
	}
}