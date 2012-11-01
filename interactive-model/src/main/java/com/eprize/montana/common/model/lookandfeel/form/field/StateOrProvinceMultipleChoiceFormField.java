package com.eprize.montana.common.model.lookandfeel.form.field;

import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;



/**
 * 
 * @author Tom.Myers
 *
 */
public class StateOrProvinceMultipleChoiceFormField extends AbstractMultipleChoiceFormField {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * <label for="state">State</label>
	 * <select name="state">
	 * <option value="">Select One</option>
	 * <optgroup label="United States">
	 * <option value="US-AL">Alabama</option>
	 * <option value="US-AK">Alaska</option>
	 * <option value="US-AZ">Arizona</option>
	 * <option value="US-AR">Arkansas</option>
	 * <option value="US-CA">California</option>
	 * <option value="US-CO">Colorado</option>
	 * <option value="US-CT">Connecticut</option>
	 * <option value="US-DE">Delaware</option>
	 * <option value="US-DC">District of Columbia</option>
	 * <option value="US-FL">Florida</option>
	 * <option value="US-GA">Georgia</option>
	 * <option value="US-HI">Hawaii</option>
	 * <option value="US-ID">Idaho</option>
	 * <option value="US-IL">Illinois</option>
	 * <option value="US-IN">Indiana</option>
	 * <option value="US-IA">Iowa</option>
	 * <option value="US-KS">Kansas</option>
	 * <option value="US-KY">Kentucky</option>
	 * <option value="US-LA">Louisiana</option>
	 * <option value="US-ME">Maine</option>
	 * <option value="US-MD">Maryland</option>
	 * <option value="US-MA">Massachusetts</option>
	 * <option value="US-MI">Michigan</option>
	 * <option value="US-MN">Minnesota</option>
	 * <option value="US-MS">Mississippi</option>
	 * <option value="US-MO">Missouri</option>
	 * <option value="US-MT">Montana</option>
	 * <option value="US-NE">Nebraska</option>
	 * <option value="US-NV">Nevada</option>
	 * <option value="US-NH">New Hampshire</option>
	 * <option value="US-NJ">New Jersey</option>
	 * <option value="US-NM">New Mexico</option>
	 * <option value="US-NY">New York</option>
	 * <option value="US-NC">North Carolina</option>
	 * <option value="US-ND">North Dakota</option>
	 * <option value="US-OH">Ohio</option>
	 * <option value="US-OK">Oklahoma</option>
	 * <option value="US-OR">Oregon</option>
	 * <option value="US-PA">Pennsylvania</option>
	 * <option value="US-RI">Rhode Island</option>
	 * <option value="US-SC">South Carolina</option>
	 * <option value="US-SD">South Dakota</option>
	 * <option value="US-TN">Tennessee</option>
	 * <option value="US-TX">Texas</option>
	 * <option value="US-UT">Utah</option>
	 * <option value="US-VT">Vermont</option>
	 * <option value="US-VA">Virginia</option>
	 * <option value="US-WA">Washington</option>
	 * <option value="US-WV">West Virginia</option>
	 * <option value="US-WI">Wisconsin</option>
	 * <option value="US-WY">Wyoming</option>
	 * <option value="US-AA">AF Americas</option>
	 * <option value="US-AE">AF Except Am. &amp; Pac.</option>
	 * <option value="US-AP">AF Pacific</option>
	 * </optgroup>
	 * </select>
	 */
	public StateOrProvinceMultipleChoiceFormField() {
		
		super();
		
		this.name = "state_or_province_field";
		this.displayLabelId = "State";

		this.allowMultipleSelections = Boolean.FALSE;

		int index = 1;
		
		this.addMultipleChoiceFormFieldElement(new MultipleChoiceFormFieldElement(
			Integer.valueOf(index++),
			PromotionViewConstants.STATE_FORM_FIELD_NAME_US_MI,
			PromotionViewConstants.STATE_FORM_FIELD_VALUE_US_MI,
			Boolean.FALSE));
		
		// TODO: TDM: Add the rest of the states/territiories.
		
		this.content = this.getDefaultContent();
	}	
}