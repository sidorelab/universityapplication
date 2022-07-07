package com.softwareeng.universityapplication.entities;

import com.softwareeng.universityapplication.entities.common.mappedSuperclasses.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * This object will contain information about a friendship
 * Maps the friendship table in the database
 */
@Data
@Entity
@Table(name = "friendship")
public class Friendship extends BaseEntity {

    /**
     * Stores information obout the user that has made the friend request
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_requested_by", referencedColumnName = "id")
    private User requestedBy;

    /**
     * Stores information about the user that has received this friend request
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_requested_to", referencedColumnName = "id")
    private User requestedTo;

    /**
     * Used to distinguish if the friend request has been accepted or if it is still pending
     */
    @Column(nullable = false)
    private Boolean active;

}
