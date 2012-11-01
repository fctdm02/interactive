package com.eprize.montana.common.model.lookandfeel.form.field;

import java.util.Map;
import java.util.TreeMap;


/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractBooleanChoiceFormField extends AbstractFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected Boolean value;
	
	/* */
	protected Boolean defaultValue = Boolean.FALSE;
	
	/**
	 * 	
	 */
	public AbstractBooleanChoiceFormField() {
		super();
	}

	/**
	 *@param defaultValue 	
	 */
	public AbstractBooleanChoiceFormField(Boolean defaultValue) {
		super();
		this.defaultValue = defaultValue;
	}
	
	/**
	 * @return the value
	 */
	public Boolean getBooleanValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setBooleanValue(Boolean value) {
		this.value = value;
	}
	
	/**
	 * @return the defaultValue
	 */
	public Boolean getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public  Map<String, String> validate() {
		Map<String, String> validationErrors = new TreeMap<String, String>();
		
		if (this.value == null) {
			validationErrors.put("", "Cannot be null");
		}
		
		return validationErrors;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		String isSelected = null;
		if (this.defaultValue.booleanValue()) {
			isSelected = "1";
		} else {
			isSelected = "0";
		}
		
		// TODO: TDM: When doing internationalization, replace this with a resource bundle look up for the given locale.
		sb.append(this.getDisplayLabelId());
		sb.append("<INPUT type='checkbox' name='");
		sb.append(this.getName());
		sb.append(" value='");
		sb.append(isSelected);
		sb.append("'");
		sb.append("' />");
		sb.append(this.getDisplayLabelId()).append(EOL);
		
		return sb.toString();
	}
}