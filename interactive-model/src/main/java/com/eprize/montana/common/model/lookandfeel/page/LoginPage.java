package com.eprize.montana.common.model.lookandfeel.page;

import com.eprize.montana.common.model.lookandfeel.form.LoginForm;


/**
 * 
 * @author Tom.Myers
 *
 */
public class LoginPage extends AbstractPage {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private LoginForm loginForm;
	
	/**
	 * 
	 */
	public LoginPage() {
		super();
	}

	/**
	 * @return the loginForm
	 */
	public LoginForm getLoginForm() {
		return loginForm;
	}

	/**
	 * @param loginForm the loginForm to set
	 */
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
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
		
		sb.append(this.loginForm.getContent());
		
		return sb.toString();
	}
}