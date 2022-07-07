package com.softwareeng.universityapplication.entities.common.mappedSuperclasses;

import com.softwareeng.universityapplication.entities.User;
import lombok.Data;

import javax.persistence.*;

/**
 * This object will be the parent of all types of user interactions
 */
@MappedSuperclass
@Data
public abstract class UserInteraction extends BaseEntity {

    /**
     * This field stores the user that has done an interaction
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "interacted_by")
    private User interactedBy;
}
