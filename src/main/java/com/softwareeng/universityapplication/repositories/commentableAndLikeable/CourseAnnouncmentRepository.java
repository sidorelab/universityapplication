package com.softwareeng.universityapplication.repositories.commentableAndLikeable;

import com.softwareeng.universityapplication.entities.commentableAndLikeable.CourseAnnouncment;
import com.softwareeng.universityapplication.repositories.common.ParentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to make the interactions to the database that relate to the course announcements
 */
@Repository
public interface CourseAnnouncmentRepository extends ParentRepository<CourseAnnouncment, Long> {
    /**
     * Finds all announcements in a course
     * @param idCourse
     * @return
     */
    List<CourseAnnouncment> findAllByCourseField_Id(Long idCourse);
}
