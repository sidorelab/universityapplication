package com.softwareeng.universityapplication.repositories.userInteraction;

import com.softwareeng.universityapplication.entities.userInteractions.Comment;
import com.softwareeng.universityapplication.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to make the interactions to the database that relate to the comments
 */
@Repository
public interface CommentRepository extends ParentRepository<Comment, Long> {

    /**
     * Finds all the comments in a specific content sorted by date when they have been updated in descending order
     * @param idContent
     * @return
     */
    List<Comment> findAllByCommentedContent_IdOrderByUpdatedAtDesc(Long idContent);
}
