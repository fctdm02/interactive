/**
 * 
 */
package com.eprize.montana.common.model.user;

/**
 * 
 * @author tmyers
 * 
 */
public final class ClearTextPassword {
    
    /* */
    private static final String PROTECTED_STRING_VALUE = "[PROTECTED]";
    
    /* */
    private String clearTextPassword;
    
    /**
     * 
     * @param clearTextPassword
     */
    public ClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }
    
    /**
     * 
     * @return
     */
    public String getClearTextPassword() {
        return this.clearTextPassword;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (that instanceof ClearTextPassword) {
            ClearTextPassword thatClearTextPassword = (ClearTextPassword)that;
            return this.getClearTextPassword().equals(thatClearTextPassword.getClearTextPassword());
        }
        return false;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.getClearTextPassword().hashCode();
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return PROTECTED_STRING_VALUE;
    }
}