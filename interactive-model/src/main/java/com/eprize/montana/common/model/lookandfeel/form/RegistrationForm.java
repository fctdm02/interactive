package com.eprize.montana.common.model.lookandfeel.form;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField;


/**
 * 
 * @author Tom.Myers
 *
 */
public class RegistrationForm extends AbstractForm {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	protected URL onlineAlternateMethodOfEntry;
		
	/**
	 * 
	 */
	public RegistrationForm() {
		super();
		name = "registration_form";
	}

	/**
	 * @return the onlineAlternateMethodOfEntry
	 */
	public URL getOnlineAlternateMethodOfEntry() {
		return onlineAlternateMethodOfEntry;
	}

	/**
	 * @param onlineAlternateMethodOfEntry the onlineAlternateMethodOfEntry to set
	 */
	public void setOnlineAlternateMethodOfEntry(URL onlineAlternateMethodOfEntry) {
		this.onlineAlternateMethodOfEntry = onlineAlternateMethodOfEntry;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.form.AbstractForm#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<FORM action='" + this.name + "'>").append(EOL);
		
		Iterator<AbstractFormField> formFieldIterator = this.formFields.iterator();
		while (formFieldIterator.hasNext()) {
			
			AbstractFormField formField = formFieldIterator.next();
			sb.append(formField.getContent());
			
		}
		
		sb.append("<INPUT type='submit' value='Enter Now' />").append(EOL);
		sb.append("</FORM>").append(EOL);
		
		return sb.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.DomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();

		validationErrors.putAll(super.validate());
		
		boolean foundFormFieldWithUniqueIdentifier = false;
		
		Iterator<AbstractFormField> iterator = this.formFields.iterator();
		while (iterator.hasNext()) {
			
			AbstractFormField formField = iterator.next();
			
			if (formField.getIsUniqueIdentifier().booleanValue()) {
				
				foundFormFieldWithUniqueIdentifier = true;
			}
		}
		
		if (!foundFormFieldWithUniqueIdentifier) {
			validationErrors.put(this.getName(), "Form field with unique identifier not found, please specify one");
		}
		
		if (onlineAlternateMethodOfEntry == null) {
			validationErrors.put(this.getName(), "onlineAlternateMethodOfEntry must be specified");
		}
		
		return validationErrors;
	}
}