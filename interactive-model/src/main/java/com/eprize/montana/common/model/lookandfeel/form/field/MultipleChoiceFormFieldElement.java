package com.eprize.montana.common.model.lookandfeel.form.field;

import com.eprize.montana.common.model.DomainObject;
import com.eprize.montana.common.model.IDomainObject;
import com.eprize.montana.common.model.lookandfeel.PromotionViewConstants;

/**
 * 
 * @author Tom.Myers
 *
 */
public class MultipleChoiceFormFieldElement extends DomainObject implements PromotionViewConstants {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private Long multipleChoiceFormFieldElementId;
	
	/* */
	protected Integer index;
	
	/* */
	protected String displayName;
	
	/* */
	protected String displayValue;
	
	/* */
	protected Boolean isSelected;
	
	/**
	 * 
	 */
	public MultipleChoiceFormFieldElement() {
		
	}
	
	/**
	 * 
	 * @param index
	 * @param displayName
	 * @param displayValue
	 * @param isSelected
	 */
	public MultipleChoiceFormFieldElement(
		Integer index,
		String displayName,
		String displayValue,
		Boolean isSelected) {
		this.setIndex(index);
		this.setDisplayName(displayName);
		this.setDisplayValue(displayValue);
		this.setIsSelected(isSelected);
	}

	/**
	 * @return the multipleChoiceFormFieldElementId
	 */
	public Long getMultipleChoiceFormFieldElementId() {
		return multipleChoiceFormFieldElementId;
	}

	/**
	 * @param multipleChoiceFormFieldElementId the multipleChoiceFormFieldElementId to set
	 */
	public void setMultipleChoiceFormFieldElementId(
			Long multipleChoiceFormFieldElementId) {
		this.multipleChoiceFormFieldElementId = multipleChoiceFormFieldElementId;
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the displayValue
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * @param displayValue the displayValue to set
	 */
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	/**
	 * @return the isSelected
	 */
	public Boolean getIsSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected the isSelected to set
	 */
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getPersistentIdentity()
	 */
	public Long getPersistentIdentity() {

		return this.multipleChoiceFormFieldElementId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#getNaturalIdentity()
	 */
	public String getNaturalIdentity() {
		
		return this.displayName + IDomainObject.NATURAL_IDENTITY_DELIMITER + this.displayValue;
		
	}
}