/**
 * 
 */
package com.eprize.montana.common.model.exception;

/**
 * 
 * @author tmyers
 * 
 */
public final class ObjectNotFoundException extends Exception {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message
	 */
    public ObjectNotFoundException(String message) {
    	super(message);    	
    }
}
