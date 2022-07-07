package com.softwareeng.universityapplication.businessLogic.dtos.common;

import lombok.Data;

@Data
public abstract class SoftDeletionDTO extends BaseDTO {
    private Boolean deleted;
}
