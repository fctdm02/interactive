package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class EmailAddressFormField extends AbstractTextFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EmailAddressFormField() {
		super();
		this.isUniqueIdentifier = Boolean.TRUE;
		this.name = "email_address_field";
		this.displayLabelId = "Email Address";
		this.content = this.getDefaultContent();
	}	
}