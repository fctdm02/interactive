/**
 * 
 */
package com.eprize.montana.common.model.exception;

/**
 * 
 * @author tmyers
 * 
 */
public final class ValidationException extends Exception {
	
	/* */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message
	 */
    public ValidationException(String message) {
    	super(message);    	
    }
    
	/**
	 * @param message
	 * @param cause
	 */
    public ValidationException(String message, Throwable cause) {
    	super(message, cause);    	
    }
    
}
