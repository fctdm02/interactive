package com.eprize.montana.common.model.lookandfeel.form.field;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Tom.Myers
 *
 */
public abstract class AbstractMultipleChoiceFormField extends AbstractFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected Boolean allowMultipleSelections = Boolean.FALSE;
	
	/* */
	protected Set<MultipleChoiceFormFieldElement> multipleChoiceFormFieldElements = new TreeSet<MultipleChoiceFormFieldElement>();
	
	/**
	 * 
	 */
	public AbstractMultipleChoiceFormField() {
		super();
	}

	/**
	 * @return the allowMultipleSelections
	 */
	public Boolean getAllowMultipleSelections() {
		return allowMultipleSelections;
	}

	/**
	 * @param allowMultipleSelections the allowMultipleSelections to set
	 */
	public void setAllowMultipleSelections(Boolean allowMultipleSelections) {
		this.allowMultipleSelections = allowMultipleSelections;
	}

	/**
	 * @return the multipleChoiceFormFieldElements
	 */
	public Set<MultipleChoiceFormFieldElement> getMultipleChoiceFormFieldElements() {
		return multipleChoiceFormFieldElements;
	}

	/**
	 * @param multipleChoiceFormFieldElements the multipleChoiceFormFieldElements to set
	 */
	public void setMultipleChoiceFormFieldElements(Set<MultipleChoiceFormFieldElement> multipleChoiceFormFieldElements) {
		this.multipleChoiceFormFieldElements = multipleChoiceFormFieldElements;
	}

	/**
	 * 
	 * @param multipleChoiceFormFieldElement
	 * @return
	 */
	public boolean addMultipleChoiceFormFieldElement(MultipleChoiceFormFieldElement multipleChoiceFormFieldElement) {
		return this.multipleChoiceFormFieldElements.add(multipleChoiceFormFieldElement);
	}
	
	/**
	 * 
	 * @param multipleChoiceFormFieldElement
	 * @return
	 */
	public boolean removeMultipleChoiceFormFieldElement(MultipleChoiceFormFieldElement multipleChoiceFormFieldElement) {
		return this.multipleChoiceFormFieldElements.remove(multipleChoiceFormFieldElement);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		// TODO: TDM: When doing internationalization, replace this with a resource bundle look up for the given locale.
		sb.append(this.getDisplayLabelId());
		sb.append("<INPUT type='select' name='");
		sb.append(this.getName());
		sb.append("' >").append(EOL);
		sb.append("<option value=''>Select One</option>").append(EOL);
		
		Iterator<MultipleChoiceFormFieldElement> iterator = this.multipleChoiceFormFieldElements.iterator();
		while (iterator.hasNext()) {
			MultipleChoiceFormFieldElement multipleChoiceFormFieldElement = iterator.next();
			
			String isSelected = "";
			if (multipleChoiceFormFieldElement.isSelected.booleanValue()) {
				isSelected = " SELECTED ";
			}
			
			sb.append("<option value='");
			sb.append(multipleChoiceFormFieldElement.getDisplayName());
			sb.append("' ");
			sb.append(isSelected);
			sb.append(">");
			sb.append(multipleChoiceFormFieldElement.getDisplayValue());
			sb.append("</option>").append(EOL);	
		}
				
		sb.append("</select>").append(EOL);
		
		return sb.toString();
	}
}