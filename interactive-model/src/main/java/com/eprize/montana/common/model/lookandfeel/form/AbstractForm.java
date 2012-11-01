package com.eprize.montana.common.model.lookandfeel.form;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.lookandfeel.PromotionLookAndFeel;
import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;
import com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractForm extends DomainObject implements PromotionViewConstants {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long formId;

	/* */
	protected String name;
	
	/* */
	protected Boolean captchaRequired = Boolean.TRUE;

	/* */
	protected String content;
	
	/* */
	protected Set<AbstractFormField> formFields = new TreeSet<AbstractFormField>();

	/* */
	protected PromotionLookAndFeel promotionLookAndFeel;
	
	/**
	 *@return 
	 */
	public abstract String getDefaultContent();
	
	/**
	 * 
	 */
	public AbstractForm() {
		
	}
	
	/**
	 * @return the formId
	 */
	public Long getFormId() {
		return formId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(Long formId) {
		this.formId = formId;
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
	 * @return the captchaRequired
	 */
	public Boolean getCaptchaRequired() {
		return captchaRequired;
	}

	/**
	 * @param captchaRequired the captchaRequired to set
	 */
	public void setCaptchaRequired(Boolean captchaRequired) {
		this.captchaRequired = captchaRequired;
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
	 * @return the formFields
	 */
	public Set<AbstractFormField> getFormFields() {
		return formFields;
	}

	/**
	 * @param formFields the formFields to set
	 */
	public void setFormFields(Set<AbstractFormField> formFields) {
		this.formFields = formFields;
	}
	
	/**
	 * 
	 * @param formField
	 * @param sequenceNumber
	 * @return
	 */
	public boolean addFormField(AbstractFormField formField, Integer sequenceNumber) {
		formField.setForm(this);
		formField.setSequenceNumber(sequenceNumber);
		return this.formFields.add(formField);
	}
	
	/**
	 * 
	 * @param formField
	 * @return
	 */
	public boolean removeFormField(AbstractFormField formField) {
		formField.setForm(null);
		// TODO: TDM: What about resequencing the remaining form fields?
		return this.formFields.remove(formField);
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

		return this.formId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {

		return this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
	
		Iterator<AbstractFormField> iterator = this.formFields.iterator();
		while (iterator.hasNext()) {
			
			AbstractFormField formField = iterator.next();
			validationErrors.putAll(formField.validate());
		}
		
		return validationErrors;
	}	
}