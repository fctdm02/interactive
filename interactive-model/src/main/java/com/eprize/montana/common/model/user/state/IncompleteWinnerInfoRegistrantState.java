package com.eprize.montana.common.model.user.state;



/**
 * 
 * @author Tom.Myers
 *
 */
public final class IncompleteWinnerInfoRegistrantState extends AbstractRegistrantState {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public IncompleteWinnerInfoRegistrantState() {
		this.setState(PlayedGameRegistrantState.class.getSimpleName());
	}
}