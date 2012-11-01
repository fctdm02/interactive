package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class CityTextFormField extends AbstractTextFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CityTextFormField() {
		super();
		this.name = "city_field";
		this.displayLabelId = "City";
		this.content = this.getDefaultContent();
	}	
}