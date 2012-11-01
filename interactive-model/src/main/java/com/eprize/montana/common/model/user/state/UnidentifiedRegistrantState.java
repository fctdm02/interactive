package com.eprize.montana.common.model.user.state;



/**
 * 
 * @author Tom.Myers
 *
 */
public final class UnidentifiedRegistrantState extends AbstractRegistrantState {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The user has "logged in", but the corresponding Registrant profile wasn't found.  
	 * This state corresponds to showing the "Registration Page"
	 */
	public UnidentifiedRegistrantState() {
		this.setState(LoggedInRegistrantState.class.getSimpleName());
	}
}