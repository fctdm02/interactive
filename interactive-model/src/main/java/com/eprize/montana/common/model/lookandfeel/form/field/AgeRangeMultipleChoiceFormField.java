package com.eprize.montana.common.model.lookandfeel.form.field;

import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;



/**
 * 
 * @author Tom.Myers
 *
 */
public class AgeRangeMultipleChoiceFormField extends AbstractMultipleChoiceFormField {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public AgeRangeMultipleChoiceFormField() {
		
		super();
		
		this.name = "age_range_field";
		this.displayLabelId = "Age";

		this.allowMultipleSelections = Boolean.FALSE;
		
		int index = 1;
		
		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_NAME_UNDER_13,
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_VALUE_UNDER_13,
			Boolean.FALSE));
		
		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_NAME_13_TO_17,
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_VALUE_13_TO_17,
			Boolean.FALSE));

		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_NAME_18_TO_24,
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_VALUE_18_TO_24,
			Boolean.FALSE));
		
		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_NAME_25_TO_35,
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_VALUE_25_TO_35,
			Boolean.FALSE));

		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_NAME_36_TO_49,
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_VALUE_36_to_49,
			Boolean.FALSE));

		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_NAME_50_PLUS,
			PromotionViewConstants.AGE_RANGE_FORM_FIELD_VALUE_50_PLUS,
			Boolean.FALSE));
		
		this.content = this.getDefaultContent();
	}	
}