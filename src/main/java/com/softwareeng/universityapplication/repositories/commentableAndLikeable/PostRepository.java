package com.softwareeng.universityapplication.repositories.commentableAndLikeable;

import com.softwareeng.universityapplication.entities.commentableAndLikeable.Post;
import com.softwareeng.universityapplication.repositories.common.ParentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to make the interactions to the database that relate to the posts
 */
@Repository
public interface PostRepository extends ParentRepository<Post, Long> {

    /**
     * Finds all the posts posted by a specific user
     * Data returned is pageable
     * @param idUser
     * @param pageable
     * @return
     */
    List<Post> findAllByPostedBy_Id(Long idUser, Pageable pageable);

    /**
     * Finds all the posts of the users that are passed in the parameter
     * Data returned is pageable
     * @param idOfFriendsOfLoggedUser
     * @param pageable
     * @return
     */
    List<Post> findAllByPostedBy_IdIsInOrderByCreatedAtDesc(List<Long> idOfFriendsOfLoggedUser, Pageable pageable);
}
