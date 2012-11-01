package com.eprize.montana.common.model.lookandfeel.form.field;

import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;



/**
 * 
 * @author Tom.Myers
 *
 */
public class GenderMultipleChoiceFormField extends AbstractMultipleChoiceFormField {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public GenderMultipleChoiceFormField() {
		
		super();
		
		this.name = "gender_field";
		this.displayLabelId = "Gender";

		this.allowMultipleSelections = Boolean.FALSE;

		int index = 1;
		
		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.GENDER_FORM_FIELD_NAME_MALE,
			PromotionViewConstants.GENDER_FORM_FIELD_VALUE_MALE,
			Boolean.FALSE));
		
		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.GENDER_FORM_FIELD_NAME_FEMALE,
			PromotionViewConstants.GENDER_FORM_FIELD_VALUE_FEMALE,
			Boolean.FALSE));
		
		this.content = this.getDefaultContent();
	}	
}