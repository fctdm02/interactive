package com.eprize.montana.common.model.lookandfeel.page;

import com.eprize.montana.common.model.lookandfeel.form.WinnerUpdateForm;


/**
 * 
 * @author Tom.Myers
 *
 */
public class WinnerUpdatePage extends AbstractPage {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private WinnerUpdateForm winnerUpdateForm;
	
	/**
	 * 
	 */
	public WinnerUpdatePage() {
		super();
	}

	/**
	 * @return the winnerUpdateForm
	 */
	public WinnerUpdateForm getWinnerUpdateForm() {
		return winnerUpdateForm;
	}

	/**
	 * @param winnerUpdateForm the winnerUpdateForm to set
	 */
	public void setWinnerUpdateForm(WinnerUpdateForm winnerUpdateForm) {
		this.winnerUpdateForm = winnerUpdateForm;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.page.AbstractPage#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<H1>You may be a winner!</H1>").append(EOL);
		sb.append("Please enter the following information so that we can verify your eligibility.").append(EOL);		
		sb.append("</P>").append(EOL);
		
		sb.append(this.winnerUpdateForm.getContent());
		
		return sb.toString();
	}
}