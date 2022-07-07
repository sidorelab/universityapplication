package com.softwareeng.universityapplication.businessLogic.dtos.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseDTO {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
