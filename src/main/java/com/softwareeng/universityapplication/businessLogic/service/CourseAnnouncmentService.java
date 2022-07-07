package com.softwareeng.universityapplication.businessLogic.service;

import com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import com.softwareeng.universityapplication.businessLogic.service.base.BaseService;

import java.util.List;

public interface CourseAnnouncmentService extends BaseService<CourseAnnouncmentDTO, Long> {
    /**
     * Returns all the announcements posted in a course
     * @param idCourse
     * @return
     */
    List<CourseAnnouncmentDTO> announcmentsOfACourse(Long idCourse);
}
