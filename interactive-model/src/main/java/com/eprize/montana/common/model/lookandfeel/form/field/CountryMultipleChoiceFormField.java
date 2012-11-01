package com.eprize.montana.common.model.lookandfeel.form.field;

import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;



/**
 * 
 * @author Tom.Myers
 *
 */
public class CountryMultipleChoiceFormField extends AbstractMultipleChoiceFormField {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CountryMultipleChoiceFormField() {
		super();
		
		this.name = "country_field";
		this.displayLabelId = "Country";
		
		this.allowMultipleSelections = Boolean.FALSE;
		
		int index = 1;
		
		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.COUNTRY_USA_COUNTRY_CODE,
			PromotionViewConstants.COUNTRY_USA_DISPLAY_NAME,
			Boolean.TRUE));
		
		this.content = this.getDefaultContent();
	}
	
}