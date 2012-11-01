package com.eprize.montana.common.model.lookandfeel.page;



/**
 * 
 * @author Tom.Myers
 *
 */
public class GamePlayPage extends AbstractPage {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public GamePlayPage() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.page.AbstractPage#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<H1>Game Play Page</H1>").append(EOL);
		
		return sb.toString();
	}
}