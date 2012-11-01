package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class LastNameTextFormField extends AbstractTextFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LastNameTextFormField() {
		super();
		this.name = "last_name_field";
		this.displayLabelId = "Last Name";
		this.content = this.getDefaultContent();
	}	
}