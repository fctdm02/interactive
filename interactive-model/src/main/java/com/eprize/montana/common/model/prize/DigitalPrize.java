/**
 * 
 */
package com.eprize.montana.common.model.prize;


/**
 * 
 * @author tmyers
 * 
 */
public class DigitalPrize extends AbstractPrize {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private String digitalCode;

	/**
	 * 
	 */
	public DigitalPrize() {
		
	}

	/**
	 * @return the digitalCode
	 */
	public String getDigitalCode() {
		return digitalCode;
	}

	/**
	 * @param digitalCode the digitalCode to set
	 */
	public void setDigitalCode(String digitalCode) {
		this.digitalCode = digitalCode;
	}	
}