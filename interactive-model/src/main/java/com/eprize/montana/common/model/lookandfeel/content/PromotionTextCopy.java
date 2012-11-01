/**
 * 
 */
package com.eprize.montana.common.model.lookandfeel.content;


/**
 * 
 * @author tmyers
 * 
 */
public class PromotionTextCopy extends AbstractPromotionCopy {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected String textContent;
	
	/**
	 * 
	 */
	public PromotionTextCopy() {
		super();
	}

	/**
	 * @return the textContent
	 */
	public String getTextContent() {
		return textContent;
	}

	/**
	 * @param textContent the textContent to set
	 */
	public void setContent(String textContent) {
		this.textContent = textContent;
	}	
}