/**
 * 
 */
package com.eprize.montana.common.model;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;

import com.eprize.montana.common.model.exception.ValidationException;
import com.eprize.montana.common.model.user.ClearTextPassword;

/**
 * 
 * @author tmyers
 *
 */
class PasswordFactory {

    /**
     * 
     */
    private PasswordFactory() {
        
    }

    /**
     * 
     * @param clearTextPassword
     * @param clearTextPasswordVerify
     * @return
     * @throws ValidationException
     */
    public static final String encodePassword(
    	ClearTextPassword clearTextPassword, 
    	ClearTextPassword clearTextPasswordVerify) 
    throws ValidationException {

        String encodedPassword = null;
        
        if (clearTextPassword == null) {
            throw new ValidationException("password cannot be null");
        }

        if (clearTextPassword.getClearTextPassword() == null || clearTextPassword.getClearTextPassword().isEmpty()) {
            throw new ValidationException("password cannot be empty");
        }

        if (clearTextPasswordVerify == null) {
            throw new ValidationException("passwordVerify cannot be null");
        }

        if (clearTextPasswordVerify.getClearTextPassword() == null || clearTextPasswordVerify.getClearTextPassword().isEmpty()) {
            throw new ValidationException("passwordVerify cannot be empty");
        }

        if (!clearTextPassword.getClearTextPassword().equals(clearTextPasswordVerify.getClearTextPassword())) {
            throw new ValidationException("password does not match passwordVerify");
        }
        
        LdapShaPasswordEncoder shaPasswordEncoder = new LdapShaPasswordEncoder();

        // TODO: TDM: Investigate as to whether each user should have their own derived salt value.
        byte[] saltBytes = "ePrize03122012".getBytes();
        encodedPassword = shaPasswordEncoder.encodePassword(clearTextPassword.getClearTextPassword(), saltBytes);
                    
        return encodedPassword;
    }
}