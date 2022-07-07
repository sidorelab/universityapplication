package com.softwareeng.universityapplication.repositories.commentableAndLikeable;

import com.softwareeng.universityapplication.entities.common.CommentableAndLikeable;
import com.softwareeng.universityapplication.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentableAndLikeableRepository extends ParentRepository<CommentableAndLikeable, Long> {
}
