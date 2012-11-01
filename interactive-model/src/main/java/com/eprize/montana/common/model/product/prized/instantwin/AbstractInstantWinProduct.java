/**
 * 
 */
package com.eprize.montana.common.model.product.prized.instantwin;

import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class AbstractInstantWinProduct extends AbstractPrizedProduct {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected String html5Content;
	
	/**
	 * 
	 */
	public AbstractInstantWinProduct() {
		super();
	}

	/**
	 * @return the html5Content
	 */
	public String getHtml5Content() {
		return html5Content;
	}

	/**
	 * @param html5Content the html5Content to set
	 */
	public void setHtml5Content(String html5Content) {
		this.html5Content = html5Content;
	}
}