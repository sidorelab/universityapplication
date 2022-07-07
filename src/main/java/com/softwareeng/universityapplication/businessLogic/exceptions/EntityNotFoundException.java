package com.softwareeng.universityapplication.businessLogic.exceptions;

import com.softwareeng.universityapplication.businessLogic.common.MessageConstants;
import com.softwareeng.universityapplication.businessLogic.exceptions.common.UniversityAppException;

/**
 * Thrown if an entity is not found in the database
 */
public class EntityNotFoundException extends UniversityAppException {

    public EntityNotFoundException() {
        super(MessageConstants.MSG_ENTITY_NOT_FOUND_EXCEPTION);
    }
}
