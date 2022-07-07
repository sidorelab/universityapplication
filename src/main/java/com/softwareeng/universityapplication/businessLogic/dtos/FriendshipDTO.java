package com.softwareeng.universityapplication.businessLogic.dtos;

import com.softwareeng.universityapplication.businessLogic.dtos.common.BaseDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class FriendshipDTO extends BaseDTO {
    private UserDTO requestedBy;
    private UserDTO requestedTo;
    private Boolean active;
}
