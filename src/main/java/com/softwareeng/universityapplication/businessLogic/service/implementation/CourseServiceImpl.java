package com.softwareeng.universityapplication.businessLogic.service.implementation;

import com.softwareeng.universityapplication.businessLogic.exceptions.EntityNotFoundException;
import com.softwareeng.universityapplication.businessLogic.exceptions.FriendshipNotFoundException;
import com.softwareeng.universityapplication.businessLogic.service.CourseService;
import com.softwareeng.universityapplication.businessLogic.service.FriendshipService;
import com.softwareeng.universityapplication.businessLogic.service.PostService;
import com.softwareeng.universityapplication.businessLogic.service.base.AbstractJpaService;
import com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable.CourseDTO;
import com.softwareeng.universityapplication.entities.User;
import com.softwareeng.universityapplication.entities.commentableAndLikeable.Course;
import com.softwareeng.universityapplication.repositories.UserRepository;
import com.softwareeng.universityapplication.repositories.commentableAndLikeable.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl extends AbstractJpaService<CourseDTO, Course, Long> implements CourseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private PostService postService;

    public CourseServiceImpl() {
        super(Course.class, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> findAllByStudentEnrolled_id(Long id, int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        return mapEntityListToDTO(getCourseRepository().findAllByStudentsEnrolled_Id(id, PageRequest.of(pageNumber, 10)));
    }

    @Override
    public List<CourseDTO> findAllByStudentEnrolled_id(Long id) {
        return mapEntityListToDTO(getCourseRepository().findAllByStudentsEnrolled_Id(id));
    }

    @Override
    public List<CourseDTO> findAllCoursesAvailableForLoggedUser(long id, int pageNumber) {
        pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
        List<Course> courses = getCourseRepository().findAllAvailableCourses(id, PageRequest.of(pageNumber, 10));
        return mapEntityListToDTO(courses);
    }

    @Override
    public void saveUser(Long courseId, Long userId) {
        Course course = prepareCourseForAddOrDrop(courseId);
        User user = prepareUserForAddOrDrop(userId);

        course.addUser(user);
        course = repo.save(course);

        this.postService.addPostFromCourseEnrollment(userId, mapFromEntity(course));
    }

    @Override
    public void dropUser(Long courseId, Long userId) {
        Course course = prepareCourseForAddOrDrop(courseId);
        User user = prepareUserForAddOrDrop(userId);

        course.dropUser(user);
        repo.save(course);
    }

    @Override
    public List<CourseDTO> getCoursesOfAFriend(Long idLoggedUser, Long idFriend, int pageNumber) {
        if(friendshipService.checkIfFriendshipAlreadyExistsAndAccepted(idLoggedUser, idFriend)) {
            pageNumber = pageNumber < 1 ? 0 : pageNumber - 1;
            return this.findAllByStudentEnrolled_id(idFriend, pageNumber);
        }
        throw new FriendshipNotFoundException();
    }

    private CourseRepository getCourseRepository() {
        return (CourseRepository) repo;
    }

    private Course prepareCourseForAddOrDrop(Long courseId) {
        Optional<Course> optionalCourse = this.repo.findById(courseId);
        Course course = null;
        if(optionalCourse.isPresent()) {
            course = optionalCourse.get();
        } else {
            throw new EntityNotFoundException();
        }
        return course;
    }

    private User prepareUserForAddOrDrop(Long userId) {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        User user = null;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new EntityNotFoundException();
        }
        return user;
    }
}
