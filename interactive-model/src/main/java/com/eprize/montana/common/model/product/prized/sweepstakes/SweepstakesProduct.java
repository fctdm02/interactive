/**
 * 
 */
package com.eprize.montana.common.model.product.prized.sweepstakes;

import com.eprize.montana.common.model.product.prized.AbstractPrizedProduct;

/**
 * 
 * @author tmyers
 * 
 */
public class SweepstakesProduct extends AbstractPrizedProduct {

	/** */
	public static final String DEFAULT_SWEEPSTAKES_DESCRIPTION = "This is a basic sweepstakes";
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public SweepstakesProduct() {
		
		super();
		this.description = DEFAULT_SWEEPSTAKES_DESCRIPTION;
	}
}