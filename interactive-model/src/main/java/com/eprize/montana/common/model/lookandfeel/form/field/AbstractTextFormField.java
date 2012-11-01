package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractTextFormField extends AbstractFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected String value = "";
	
	/**
	 * 	
	 */
	public AbstractTextFormField() {
		super();
	}
	
	/**
	 * @return the value
	 */
	public String getTextValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setTextValue(String value) {
		this.value = value;
	}
		
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		// TODO: TDM: When doing internationalization, replace this with a resource bundle look up for the given locale.
		sb.append(this.getDisplayLabelId());
		sb.append("<INPUT type='text' name='");
		sb.append(this.getName());
		sb.append("' />").append(EOL);
		
		return sb.toString();
	}
}