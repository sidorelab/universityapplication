package com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable;

import com.softwareeng.universityapplication.businessLogic.dtos.common.CommentableAndLikeableDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseAnnouncmentDTO extends CommentableAndLikeableDTO {
    private CourseDTO courseField;

    private String content;
}
