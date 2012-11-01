package com.eprize.montana.common.model.lookandfeel.page;



/**
 * 
 * @author Tom.Myers
 *
 */
public class ThankYouPage extends AbstractPage {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public ThankYouPage() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.page.AbstractPage#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<H1>Thank You Page</H1>").append(EOL);
		
		return sb.toString();
	}
}