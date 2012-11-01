package com.eprize.montana.common.model.lookandfeel.page;



/**
 * 
 * @author Tom.Myers
 *
 */
public class ConfirmationPage extends AbstractPage {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public ConfirmationPage() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.page.AbstractPage#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<H1>Confirmation Page</H1>").append(EOL);
		
		return sb.toString();
	}
}