package com.softwareeng.universityapplication.businessLogic.exceptions;

import com.softwareeng.universityapplication.businessLogic.common.MessageConstants;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;

/**
 * Thrown if we are trying to sign up with an existing email
 */
public class EmailViolationException extends UniversityAppException {
    public EmailViolationException() {
        super(MessageConstants.MSG_EMAIL_EXISTS);
    }
}
