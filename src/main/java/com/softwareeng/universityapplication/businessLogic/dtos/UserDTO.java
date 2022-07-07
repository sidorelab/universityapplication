package com.softwareeng.universityapplication.businessLogic.dtos;

import com.softwareeng.universityapplication.businessLogic.dtos.common.SoftDeletionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SoftDeletionDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String profilePicturePath;
}
