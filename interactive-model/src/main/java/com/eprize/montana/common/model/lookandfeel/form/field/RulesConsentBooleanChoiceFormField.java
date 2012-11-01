package com.eprize.montana.common.model.lookandfeel.form.field;



/**
 * 
 * @author Tom.Myers
 *
 */
public class RulesConsentBooleanChoiceFormField extends AbstractBooleanChoiceFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public RulesConsentBooleanChoiceFormField() {
		super(Boolean.TRUE);
		this.name = "rules_consent_field";
		this.displayLabelId = "I agree to the official rules.";
		this.content = this.getDefaultContent();
	}	
}