package com.softwareeng.universityapplication.businessLogic.dtos.common;

import com.softwareeng.universityapplication.businessLogic.dtos.UserDTO;
import lombok.Data;

@Data
public abstract class UserInteractionDTO extends BaseDTO {
    private UserDTO interactedBy;
}
