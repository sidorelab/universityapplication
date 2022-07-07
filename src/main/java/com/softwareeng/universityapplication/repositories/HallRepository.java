package com.softwareeng.universityapplication.repositories;

import com.softwareeng.universityapplication.entities.Hall;
import com.softwareeng.universityapplication.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to make the interactions to the database that relate to the halls
 */
@Repository
public interface HallRepository extends ParentRepository<Hall, Long> {
}
