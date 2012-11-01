/**
 * 
 */
package com.eprize.montana.common.model.user;


/**
 * 
 * @author tmyers
 * 
 */
public class SuperUserAccountCoordinator extends AccountCoordinator {

	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public SuperUserAccountCoordinator() {
		
	}
	
	/**
	 * A super user is able to create/update/delete other account coordinators.
	 * @return
	 */
	public boolean isSuperUser() { 
		return true;
	}
	
}