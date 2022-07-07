package com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable;

import com.softwareeng.universityapplication.businessLogic.dtos.common.CommentableAndLikeableDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.enums.CourseRepeatType;
import com.softwareeng.universityapplication.businessLogic.dtos.HallDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseDTO extends CommentableAndLikeableDTO {
    private String name;

    private String description;

    private HallDTO hall;

    private Date startDateTime;

    private int repeatCount;

    private CourseRepeatType repeatType;
}
