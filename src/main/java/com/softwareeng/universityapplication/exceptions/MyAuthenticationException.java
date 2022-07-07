package com.softwareeng.universityapplication.exceptions;

import com.softwareeng.universityapplication.constants.SecurityConstants;
import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception thrown when the jwt token is not vali
 */
public class MyAuthenticationException extends AuthenticationException {
    public MyAuthenticationException() {
        super(SecurityConstants.TOKEN_INVALID);
    }
}
