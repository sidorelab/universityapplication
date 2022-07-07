package com.softwareeng.universityapplication.businessLogic.exceptions;

import com.softwareeng.universityapplication.businessLogic.common.MessageConstants;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;

/**
 * Thrown if a user is not found in the authentication phase
 */
public class UserNotFoundException extends UniversityAppException {
    public UserNotFoundException() {
        super(MessageConstants.MSG_USER_NOT_FOUND_EXCEPTION);
    }
}
