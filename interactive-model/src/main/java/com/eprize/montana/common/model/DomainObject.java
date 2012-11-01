/**
 * 
 */
package com.eprize.montana.common.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author tmyers
 * 
 */
public abstract class DomainObject implements IDomainObject, Comparable<DomainObject>, Serializable {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	private Integer version;
	
	/* */
	private boolean isDeletable = true; 

    /* */
    private boolean isModifiable = true; 
	
	/**
	 * 
	 */
	public DomainObject() {
	}
	
	/**
	 * 
	 * @return version
	 */
	public final Integer getVersion() {
		return this.version;
	}
	
	/**
	 * 
	 * @param version
	 */
	public final void setVersion(Integer version) {
		this.version = version;
	}

    /**
     * 
     * @return isDeletable
     */
    public final boolean getIsDeletable() {
        return this.isDeletable;
    }
    
    /**
     * 
     * @param isDeletable
     */
    public final void setIsDeletable(boolean isDeletable) {
        this.isDeletable = isDeletable;
    }

    /**
     * 
     * @return isModifiable
     */
    public final boolean getIsModifiable() {
        return this.isModifiable;
    }
    
    /**
     * 
     * @param isModifiable
     */
    public final void setIsModifiable(boolean isModifiable) {
        this.isModifiable = isModifiable;
    }

    /*
     * (non-Javadoc)
     * @see com.eprize.montana.common.model.IDomainObject#getPersistentIdentity()
     */
	public abstract Long getPersistentIdentity();
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.IDomainObject#getNaturalIdentity()
	 */
	public abstract String getNaturalIdentity();

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.IDomainObject#validate()
	 */
	public Map<String, String> validate() {
		
		Map<String, String> validationErrors = new TreeMap<String, String>();
		return validationErrors;
	}
			    
    /**
     * The length of the string is trimmed at <code>MAX_STRING_LENGTH</code> 
     * characters.  It is expected that domain object setters use this method 
     * for required string fields.
     * 
     * @param string
     * @return
     */
    public static String trimString(final String string) {
        
        String returnValue = string;
        if (returnValue != null) {
            returnValue = returnValue.trim();
            if (returnValue.length() >= MAX_STRING_LENGTH) {
                returnValue = returnValue.substring(0, MAX_STRING_LENGTH-1);
            }
        }
        return returnValue;
    }
	
   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   public final int hashCode() {
       
      if (this.getPersistentIdentity() != null) {
          return this.getPersistentIdentity().hashCode();  
      }        
      
      return this.getNaturalIdentity().hashCode();
   }
	
   /*
    * (non-Javadoc)
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(DomainObject that) {
      
        return this.getNaturalIdentity().compareTo(((DomainObject)that).getNaturalIdentity());
   }
   
   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public final boolean equals(Object that) {
      
      if (this.getPersistentIdentity() != null && that instanceof DomainObject && ((DomainObject)that).getPersistentIdentity() != null) {
    	  
    	  return this.getPersistentIdentity().equals(((DomainObject)that).getPersistentIdentity());
    	  
      } else if (this.getPersistentIdentity() != null && that instanceof Long) {
    	  
    	  return this.getPersistentIdentity().equals(that);

      }
      
      if (that instanceof DomainObject) {
    	  return this.getNaturalIdentity().equals(((DomainObject)that).getNaturalIdentity());  
      }
      
      return false;
   }
   
   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
	public String toString() {
		
		return this.getNaturalIdentity();
	}
 
	/*
	 * 
	 * @param attributeValue
	 * @param attributeName
	 * @param validationErrors
	 */
	protected void validateRequiredAttribute(String attributeValue, String attributeName, Map<String, String> validationErrors) {
		
		if (attributeValue == null || attributeValue.isEmpty()) {
			validationErrors.put(attributeName, "Cannot be null or empty");
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.IDomainObject#getCost()
	 */
	public int getCost() {
		return 0;
	}
}