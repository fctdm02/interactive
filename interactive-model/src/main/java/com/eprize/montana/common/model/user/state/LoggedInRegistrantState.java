package com.eprize.montana.common.model.user.state;



/**
 * 
 * @author Tom.Myers
 *
 */
public final class LoggedInRegistrantState extends AbstractRegistrantState {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public LoggedInRegistrantState() {
		this.setState(LoggedInRegistrantState.class.getSimpleName());
	}
}