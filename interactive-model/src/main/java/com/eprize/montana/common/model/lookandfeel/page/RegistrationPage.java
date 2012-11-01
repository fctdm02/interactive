package com.eprize.montana.common.model.lookandfeel.page;

import com.eprize.montana.common.model.lookandfeel.form.RegistrationForm;


/**
 * 
 * @author Tom.Myers
 *
 */
public class RegistrationPage extends AbstractPage {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private RegistrationForm registrationForm;
	
	/**
	 * 
	 */
	public RegistrationPage() {
		super();
	}

	/**
	 * @return the registrationForm
	 */
	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	/**
	 * @param registrationForm the registrationForm to set
	 */
	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.page.AbstractPage#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<H1>Enter and play now for the chance to walk away a winner!</H1>").append(EOL);
		sb.append("Wouldn't you love to win [%%SWEEPS_GRAND_PRIZE%%]? Enter now and that dream just might become your reality. ").append(EOL);		
		sb.append("Plus, you get to play for a shot at winning [%%INSTANT_WIN_PRIZE%%] instantly!").append(EOL);
		sb.append("</P>").append(EOL);
		sb.append("Submit your email address to enter and play now:").append(EOL);
		sb.append("</P>").append(EOL);
		
		sb.append(this.registrationForm.getContent());
		
		return sb.toString();
	}
}