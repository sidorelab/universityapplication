package com.softwareeng.universityapplication.businessLogic.service.implementation;

import com.softwareeng.universityapplication.businessLogic.dtos.UserDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.common.CommentableAndLikeableDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.userInteractions.CommentDTO;
import com.softwareeng.universityapplication.businessLogic.exceptions.ContentEmptyException;
import com.softwareeng.universityapplication.businessLogic.service.CommentService;
import com.softwareeng.universityapplication.businessLogic.service.CommentableAndLikeableService;
import com.softwareeng.universityapplication.businessLogic.service.PostService;
import com.softwareeng.universityapplication.businessLogic.service.UserService;
import com.softwareeng.universityapplication.businessLogic.service.base.AbstractJpaService;
import com.softwareeng.universityapplication.entities.userInteractions.Comment;
import com.softwareeng.universityapplication.repositories.userInteraction.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CommentServiceImpl extends AbstractJpaService<CommentDTO, Comment, Long> implements CommentService {

    @Autowired
    private CommentableAndLikeableService commentableAndLikeableService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public CommentServiceImpl() {
        super(Comment.class, CommentDTO.class);
    }

    @Override
    public void addCommentToACommentableAndLikeable(Long idCommentableAndLikeable, String content, Long idUser) {
        if(!StringUtils.hasText(content)) {
            throw new ContentEmptyException();
        }
        CommentableAndLikeableDTO commentableAndLikeableDTO = this.commentableAndLikeableService.findById(idCommentableAndLikeable);
        UserDTO userDTO = this.userService.findById(idUser);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setInteractedBy(userDTO);
        commentDTO.setContent(content);
        commentDTO.setCommentedContent(commentableAndLikeableDTO);

        commentDTO = this.save(commentDTO);
        this.postService.addPostFromComment(userDTO.getId(), commentDTO);
    }

    @Override
    public List<CommentDTO> getAllCommentsInACommentableAndLikeable(Long idCommentableAndLikeable) {
        return mapEntityListToDTO(getCommentRepository().findAllByCommentedContent_IdOrderByUpdatedAtDesc(idCommentableAndLikeable));
    }

    private CommentRepository getCommentRepository() {
        return (CommentRepository) repo;
    }


}
