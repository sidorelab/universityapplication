package com.softwareeng.universityapplication.businessLogic.exceptions;

import com.softwareeng.universityapplication.businessLogic.common.MessageConstants;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;

/**
 * Thrown if the content of a comment, or post is empty
 */
public class ContentEmptyException extends UniversityAppException {

    public ContentEmptyException() {
        super(MessageConstants.MSG_COMMENT_EMPTY_EXCEPTION);
    }
}
