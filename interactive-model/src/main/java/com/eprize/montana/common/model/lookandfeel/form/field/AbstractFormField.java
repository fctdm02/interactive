package com.eprize.montana.common.model.lookandfeel.form.field;

import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;
import com.eprize.montana.common.model.lookandfeel.form.AbstractForm;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractFormField extends DomainObject implements PromotionViewConstants {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long formFieldId;

	/* */
	protected String displayLabelId;
	
	/* */
	protected String name;
	
	/* */
	protected Boolean isRequired = Boolean.TRUE;
	
	/* */
	protected Integer sequenceNumber = Integer.valueOf(0);
	
	/* For each form, only one field can have this attribute be set to true (i.e. there must be one and only one field with this set to true) */
	protected Boolean isUniqueIdentifier = Boolean.FALSE;

	/* */
	protected String content = "";
	
	/* */
	protected AbstractForm form;
	
	/**
	 *@return 
	 */
	public abstract String getDefaultContent();
	
	/**
	 * 
	 */
	public AbstractFormField() {
	}	
	
	/**
	 * @return the formFieldId
	 */
	public Long getFormFieldId() {
		return formFieldId;
	}

	/**
	 * @param formFieldId the formFieldId to set
	 */
	public void setFormFieldId(Long formFieldId) {
		this.formFieldId = formFieldId;
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
	 * @return the displayLabelId
	 */
	public String getDisplayLabelId() {
		return displayLabelId;
	}

	/**
	 * @param displayLabelId the displayLabelId to set
	 */
	public void setDisplayLabel(String displayLabelId) {
		this.displayLabelId = displayLabelId;
	}

	/**
	 * @return the isRequired
	 */
	public Boolean getIsRequired() {
		return isRequired;
	}

	/**
	 * @param isRequired the isRequired to set
	 */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
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
	 * @return the isUniqueIdentifier
	 */
	public Boolean getIsUniqueIdentifier() {
		return isUniqueIdentifier;
	}

	/**
	 * @param isUniqueIdentifier the isUniqueIdentifier to set
	 */
	public void setIsUniqueIdentifier(Boolean isUniqueIdentifier) {
		this.isUniqueIdentifier = isUniqueIdentifier;
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
	 * @return the form
	 */
	public AbstractForm getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(AbstractForm form) {
		this.form = form;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {

		return this.formFieldId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

		return this.sequenceNumber + UNDERSCORE + this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();

		if (this.name == null || this.name.isEmpty()) {
			validationErrors.put(this.getClass().getSimpleName(), "Name cannot be empty");
		}
		
		if (this.content == null || this.content.isEmpty()) {
			validationErrors.put(this.getName(), "Content cannot be empty");
		}

		if (this.displayLabelId == null || this.displayLabelId.isEmpty()) {
			validationErrors.put(this.getName(), "Display Label cannot be empty");
		}
		
		if (this.form == null) {
			validationErrors.put(this.getName(), "Parent Form cannot be null");
		}
		
		if (this.isRequired == null) {
			validationErrors.put(this.getName(), "Is Required cannot be null");
		}
		
		if (this.sequenceNumber == null || this.sequenceNumber.intValue() <= 0) {
			validationErrors.put(this.getName(), "Sequence Number must be nonzero");
		}
				
		return validationErrors;
	}
}