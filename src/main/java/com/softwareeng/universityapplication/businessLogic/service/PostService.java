package com.softwareeng.universityapplication.businessLogic.service;

import com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable.CourseDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable.PostDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.userInteractions.CommentDTO;
import com.softwareeng.universityapplication.businessLogic.service.base.BaseService;

import java.util.List;

public interface PostService extends BaseService<PostDTO, Long> {

    /**
     * Adds a post from the poster
     * @param content
     * @param idPostedBy
     */
    void addPostToTimeline(String content, Long idPostedBy);

    /**
     * Creates a post from a comment
     * @param idPostedBy
     * @param commentDTO
     */
    void addPostFromComment(Long idPostedBy, CommentDTO commentDTO);

    /**
     * Creates a post if a user enrolles in a coures
     * @param idPostedBy
     * @param courseDTO
     */
    void addPostFromCourseEnrollment(Long idPostedBy, CourseDTO courseDTO);

    /**
     * Returns all the posts posted by a user in a pageable way
     * @param idUser
     * @param pageNumber
     * @return
     */
    List<PostDTO> getPostsOfAUser(Long idUser, int pageNumber);

    /**
     * Returns all the posts that can be shown to the logged user
     * @param idLoggedUser
     * @param pageNumber
     * @return
     */
    List<PostDTO> getPostsToShowToLoggedUser(Long idLoggedUser, int pageNumber);
}
