package com.eprize.montana.common.model.lookandfeel.page;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;
import com.eprize.montana.common.model.lookandfeel.deliverychannel.AbstractDeliveryChannel;
import com.eprize.montana.common.model.user.state.AbstractRegistrantState;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractPage extends DomainObject implements PromotionViewConstants {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long pageId;

	/* */
	protected String name;
	
	/* */
	protected AbstractRegistrantState registrantState;
	
	/* */
	protected Integer sequenceNumber = Integer.valueOf(1);
	
	/* */
	protected String content;
	
	/* */
	protected AbstractDeliveryChannel abstractDeliveryChannel;

	/**
	 *@return 
	 */
	public abstract String getDefaultContent();
	
	/**
	 * 
	 */
	public AbstractPage() {
		this.name = this.getClass().getSimpleName();
	}
	
	/**
	 * @return the pageId
	 */
	public Long getPageId() {
		return pageId;
	}

	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(Long pageId) {
		this.pageId = pageId;
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
	 * @return the registrantState
	 */
	public AbstractRegistrantState getRegistrantState() {
		return registrantState;
	}

	/**
	 * @param registrantState the registrantState to set
	 */
	public void setRegistrantState(AbstractRegistrantState registrantState) {
		this.registrantState = registrantState;
	}

	/**
	 * @return the sequenceNumber
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the abstractDeliveryChannel
	 */
	public AbstractDeliveryChannel getAbstractDeliveryChannel() {
		return abstractDeliveryChannel;
	}

	/**
	 * @param abstractDeliveryChannel the abstractDeliveryChannel to set
	 */
	public void setAbstractDeliveryChannel(AbstractDeliveryChannel abstractDeliveryChannel) {
		this.abstractDeliveryChannel = abstractDeliveryChannel;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {

		return this.pageId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

		return this.name;
	}	
}