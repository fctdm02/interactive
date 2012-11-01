package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class AddressTwoTextFormField extends AbstractTextFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AddressTwoTextFormField() {
		super();
		this.isRequired = Boolean.FALSE;		
		this.name = "address_two_field";
		this.displayLabelId = "Apt/Suite";
		this.content = this.getDefaultContent();
	}	
}