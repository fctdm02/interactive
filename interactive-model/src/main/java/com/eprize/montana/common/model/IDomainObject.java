package com.eprize.montana.common.model;

import java.util.Map;

/**
 * 
 * @author Tom.Myers
 */
public interface IDomainObject {
	
	/** */
	String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd" ;

	/** Used to generate a domain object's natural identity. */
	String NATURAL_IDENTITY_DELIMITER = " ";
	
	/** */
	int MAX_STRING_LENGTH = 256;
	
	/**
     * From a logical perspective, the <code>persistent identity</code> of a domain 
     * object is equivalent to the notion of "object identity" in that this attribute 
     * serves to uniquely identify a domain object, except that when its value is
     * <code>null</code>, the domain object has no persistent state (yet).  In 
     * hibernate parlance, this means that the object is in a "transient" state.
     * <p>
	 * 
	 * @return The persisted identifier for this instance
	 */
	abstract Long getPersistentIdentity();
	
	/**
     * When the persistent identity is null, then the domain object can be uniquely 
     * identified by its <code>natural identity</code>.  <code>equals</code>, 
     * <code>hashCode</code> and <code>Comparable</code> use the object identity if
     * non-null, and natural identity otherwise.
	 * 
	 * @return The instance's natural identity (independent of its persistent identity) 
	 */
	abstract String getNaturalIdentity();
	
	/**
	 * Used by the domain object factory and various update methods on the 
	 * <code>IManagementService</code> interface to ensure that a domain object
	 * is in a valid state for creation and/or updating.
	 * 
	 * @return a map of field/reason pairs outlining what isn't valid.
	 */
	abstract Map<String, String> validate();
	
	/**
	 * 
	 * @return a whole dollar amount (in USD) corresponding to the cost associated with a particular
	 * promotion "configuration" choice.
	 */
	abstract int getCost();
}