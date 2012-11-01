package com.eprize.montana.common.model.promotion.state;


/**
 * 
 * @author Tom.Myers
 *
 */
public final class PendingPromotionState extends AbstractPromotionState {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public PendingPromotionState() {
		this.setState(PendingPromotionState.class.getSimpleName());
	}
}