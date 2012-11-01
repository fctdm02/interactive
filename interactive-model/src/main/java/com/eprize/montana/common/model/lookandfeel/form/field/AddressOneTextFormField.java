package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class AddressOneTextFormField extends AbstractTextFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AddressOneTextFormField() {
		super();
		this.name = "address_one_field";
		this.displayLabelId = "Address";
		this.content = this.getDefaultContent();
	}	
}