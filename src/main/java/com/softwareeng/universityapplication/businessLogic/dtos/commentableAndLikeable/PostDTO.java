package com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable;

import com.softwareeng.universityapplication.businessLogic.dtos.common.CommentableAndLikeableDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.userInteractions.CommentDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostDTO extends CommentableAndLikeableDTO {
    private String content;

    private UserDTO postedBy;

    private CommentDTO bornByComment;

    private CourseDTO bornByCourseEnrollment;
}
