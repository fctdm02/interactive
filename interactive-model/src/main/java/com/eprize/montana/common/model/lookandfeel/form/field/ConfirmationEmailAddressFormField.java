package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class ConfirmationEmailAddressFormField extends EmailAddressFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected String confirmationEmailAddressValue;
	
	/**
	 * 
	 */
	public ConfirmationEmailAddressFormField() {
		super();		
		this.name = "confirmation_email_address_field";
		this.displayLabelId = "Confirm Email";
	}
	
	/**
	 * @return the confirmationEmailAddressValue
	 */
	public String getConfirmationEmailAddressValue() {
		return confirmationEmailAddressValue;
	}

	/**
	 * @param confirmationEmailAddressValue the confirmationEmailAddressValue to set
	 */
	public void setConfirmationEmailAddressValue(String confirmationEmailAddressValue) {
		this.confirmationEmailAddressValue = confirmationEmailAddressValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append(super.getContent());
		
		// TODO: TDM: When doing internationalization, replace this with a resource bundle look up for the given locale.
		sb.append(this.getDisplayLabelId());
		sb.append("<INPUT type='text' name='");
		sb.append(this.getName());
		sb.append("' />").append(EOL);
		
		return sb.toString();
	}
}