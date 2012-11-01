package com.eprize.montana.common.model.lookandfeel.form;

import java.util.Iterator;

import com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField;


/**
 * 
 * @author Tom.Myers
 *
 */
public class WinnerUpdateForm extends AbstractForm {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public WinnerUpdateForm() {
		super();
		name = "winner_update_form";
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.form.AbstractForm#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
	
		sb.append("<H1>Winner Update</H1>");
		sb.append("<FORM action='" + this.name + "'>").append(EOL);
		
		Iterator<AbstractFormField> formFieldIterator = this.formFields.iterator();
		while (formFieldIterator.hasNext()) {
			
			AbstractFormField formField = formFieldIterator.next();
			sb.append(formField.getContent());
		}
		
		sb.append("<INPUT type='submit' value='Update' />").append(EOL);
		sb.append("</FORM>").append(EOL);
		
		return sb.toString();
	}
}