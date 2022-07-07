package com.softwareeng.universityapplication.businessLogic.dtos.userInteractions;

import com.softwareeng.universityapplication.businessLogic.dtos.common.CommentableAndLikeableDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.common.UserInteractionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentDTO extends UserInteractionDTO {
    private String content;

    private CommentableAndLikeableDTO commentedContent;
}
