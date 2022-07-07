package com.softwareeng.universityapplication.entities.userInteractions;

import com.softwareeng.universityapplication.entities.common.CommentableAndLikeable;
import com.softwareeng.universityapplication.entities.common.mappedSuperclasses.UserInteraction;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * This object will be mapped to the comment table in the database
 */
@Data
@Entity
@Table(name = "comment")
public class Comment extends UserInteraction {

    /**
     * Information regarding what the user has commented
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * Information regarding all likes that this comment has
     */
    @OneToMany(mappedBy = "commentLiked")
    private List<Like> likesOfComment;

    /**
     * Information regarding the content that this comment is posted
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "commented_content")
    private CommentableAndLikeable commentedContent;
}
