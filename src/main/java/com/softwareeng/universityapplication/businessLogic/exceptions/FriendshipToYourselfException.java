package com.softwareeng.universityapplication.businessLogic.exceptions;

import com.softwareeng.universityapplication.businessLogic.common.MessageConstants;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;

/**
 * Thrown if a user is trying to send a friendship to himself
 */
public class FriendshipToYourselfException extends UniversityAppException {
    public FriendshipToYourselfException() {
        super(MessageConstants.MSG_FRIENDSHIP_SAME_USER);
    }
}
