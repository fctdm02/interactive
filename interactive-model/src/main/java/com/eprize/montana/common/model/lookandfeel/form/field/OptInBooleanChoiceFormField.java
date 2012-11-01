package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class OptInBooleanChoiceFormField extends AbstractBooleanChoiceFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public OptInBooleanChoiceFormField() {
		super(Boolean.TRUE);
		this.name = "opt_in_field";
		this.displayLabelId = "YES! Please send me news, updates, and offers.";
		this.content = this.getDefaultContent();
	}	
}