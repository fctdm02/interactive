package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class ZipOrPostalCodeTextFormField extends AbstractTextFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ZipOrPostalCodeTextFormField() {
		super();
		this.name = "zip_or_postal_code_field";
		this.displayLabelId = "Zip Code";
		this.content = this.getDefaultContent();
	}	
}