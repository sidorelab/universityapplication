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
public class HallDTO extends SoftDeletionDTO {
    private String name;

    private Double latitude;

    private Double longitude;

    private String description;
}
