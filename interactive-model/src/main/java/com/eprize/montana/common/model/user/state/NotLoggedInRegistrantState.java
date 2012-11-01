package com.eprize.montana.common.model.user.state;



/**
 * 
 * @author Tom.Myers
 *
 */
public final class NotLoggedInRegistrantState extends AbstractRegistrantState {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public NotLoggedInRegistrantState() {
		this.setState(NotLoggedInRegistrantState.class.getSimpleName());
	}
}