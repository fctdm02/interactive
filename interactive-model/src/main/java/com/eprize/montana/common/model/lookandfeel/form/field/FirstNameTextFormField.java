package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class FirstNameTextFormField extends AbstractTextFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public FirstNameTextFormField() {
		super();
		this.name = "first_name_field";
		this.displayLabelId = "First Name";
		this.content = this.getDefaultContent();
	}	
}