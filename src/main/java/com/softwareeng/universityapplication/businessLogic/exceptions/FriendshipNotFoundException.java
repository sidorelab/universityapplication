package com.softwareeng.universityapplication.businessLogic.exceptions;

import com.softwareeng.universityapplication.businessLogic.common.MessageConstants;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;

/**
 * Thrown if the friendship between two users is not found
 */
public class FriendshipNotFoundException extends UniversityAppException {

    public FriendshipNotFoundException() {
        super(MessageConstants.MSG_FRIENDSHIP_NOT_FOUND);
    }
}
