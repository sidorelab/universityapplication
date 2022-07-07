package com.softwareeng.universityapplication.repositories.common;

import com.softwareeng.universityapplication.entities.common.mappedSuperclasses.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * A generic base repository that all the other repositories will extend from, to reduce dublicated codes
 * @param <ENTITY>
 * @param <ID>
 */
@NoRepositoryBean
public interface ParentRepository<ENTITY extends BaseEntity, ID> extends JpaRepository<ENTITY, ID> {
}
