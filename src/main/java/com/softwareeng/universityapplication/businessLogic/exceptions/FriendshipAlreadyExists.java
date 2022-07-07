package com.softwareeng.universityapplication.businessLogic.exceptions;

import com.softwareeng.universityapplication.businessLogic.common.MessageConstants;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;

/**
 * Thrown if the friendship between two users already exists
 */
public class FriendshipAlreadyExists extends UniversityAppException {

    public FriendshipAlreadyExists() {
        super(MessageConstants.MSG_FRIENDSHIP_ALREADY_EXISTS);
    }
}
