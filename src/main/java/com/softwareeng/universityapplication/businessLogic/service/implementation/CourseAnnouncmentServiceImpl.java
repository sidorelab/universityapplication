package com.softwareeng.universityapplication.businessLogic.service.implementation;

import com.softwareeng.universityapplication.businessLogic.service.base.AbstractJpaService;
import com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import com.softwareeng.universityapplication.businessLogic.service.CourseAnnouncmentService;
import com.softwareeng.universityapplication.entities.commentableAndLikeable.CourseAnnouncment;
import com.softwareeng.universityapplication.repositories.commentableAndLikeable.CourseAnnouncmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseAnnouncmentServiceImpl extends AbstractJpaService<CourseAnnouncmentDTO, CourseAnnouncment, Long> implements CourseAnnouncmentService {
    public CourseAnnouncmentServiceImpl() {
        super(CourseAnnouncment.class, CourseAnnouncmentDTO.class);
    }

    @Override
    public List<CourseAnnouncmentDTO> announcmentsOfACourse(Long idCourse) {
        List<CourseAnnouncment> courseAnnouncments = getCourseAnnouncementRepository().findAllByCourseField_Id(idCourse);
        return mapEntityListToDTO(courseAnnouncments);
    }

    public CourseAnnouncmentRepository getCourseAnnouncementRepository() {
        return (CourseAnnouncmentRepository) repo;
    }
}
