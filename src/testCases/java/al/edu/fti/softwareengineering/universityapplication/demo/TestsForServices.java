package al.edu.fti.softwareengineering.universityapplication.demo;

import com.softwareeng.universityapplication.businessLogic.dtos.UserDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable.CourseAnnouncmentDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.commentableAndLikeable.CourseDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.userInteractions.CommentDTO;
import com.softwareeng.universityapplication.businessLogic.exceptions.ContentEmptyException;
import com.softwareeng.universityapplication.businessLogic.service.CommentService;
import com.softwareeng.universityapplication.businessLogic.service.CourseAnnouncmentService;
import com.softwareeng.universityapplication.businessLogic.service.CourseService;
import com.softwareeng.universityapplication.businessLogic.service.UserService;
import com.softwareeng.universityapplication.entities.User;
import com.softwareeng.universityapplication.entities.commentableAndLikeable.Course;
import com.softwareeng.universityapplication.entities.commentableAndLikeable.CourseAnnouncment;
import com.softwareeng.universityapplication.entities.userInteractions.Comment;
import com.softwareeng.universityapplication.repositories.FriendshipRepository;
import com.softwareeng.universityapplication.repositories.UserRepository;
import com.softwareeng.universityapplication.repositories.commentableAndLikeable.CommentableAndLikeableRepository;
import com.softwareeng.universityapplication.repositories.commentableAndLikeable.CourseAnnouncmentRepository;
import com.softwareeng.universityapplication.repositories.commentableAndLikeable.CourseRepository;
import com.softwareeng.universityapplication.repositories.userInteraction.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestsForServices {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserService userService;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @MockBean
    private CourseAnnouncmentRepository courseAnnouncmentRepository;

    @Autowired
    private CourseAnnouncmentService courseAnnouncmentService;

    @Autowired
    private CommentableAndLikeableRepository commentableAndLikeableRepository;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Test
    void testFindByEmail() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setFirstName("Student1");
        user.setLastName("Test");
        user.setPassword("test123");
        user.setProfilePicturePath("/test123");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(java.util.Optional.of(user));

        UserDTO userDTO = this.userService.findByEmail(user.getEmail());

        verify(userRepository, times(1)).findByEmail(user.getEmail());

        assertNotNull(userDTO);
        assertTrue(userDTO.getEmail().equals(user.getEmail()));
        assertTrue(userDTO.getFirstName().equals(user.getFirstName()));
        assertTrue(userDTO.getLastName().equals(user.getLastName()));
        assertTrue(userDTO.getPassword().equals(user.getPassword()));
        assertTrue(userDTO.getProfilePicturePath().equals(user.getProfilePicturePath()));
    }

    @Test
    void testGetUsersEnrolledInACourse() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setFirstName("Student1");
        user.setLastName("Test");
        user.setPassword("test123");
        user.setProfilePicturePath("/test123");

        User user1 = new User();
        user1.setEmail("Student2@gmail.com");
        user1.setFirstName("Test");
        user1.setLastName("");
        user1.setPassword("test123");
        user1.setProfilePicturePath("/test123");


        List<User> usersList = new ArrayList<>();
        usersList.add(user);
        usersList.add(user1);

        when(userRepository.findAllByCoursesOfAUser_idAndIdIsNot(1L, 10L)).thenReturn(usersList);

        List<UserDTO> userDTOS = this.userService.getUsersEnrolledInACourse(1L, 10L);

        verify(userRepository, times(1)).findAllByCoursesOfAUser_idAndIdIsNot(1L, 10L);

        assertNotNull(userDTOS);
        assertTrue(userDTOS.size() == 3);
        assertTrue(userDTOS.get(0).getEmail().equals(user.getEmail()));

    }

    @Test
    void testAnnouncmentsOfACourse() {
        CourseAnnouncment courseAnnouncment = new CourseAnnouncment();
        courseAnnouncment.setContent("Ky eshte nje njoftim");
        List<CourseAnnouncment> courseAnnouncments = new ArrayList<>();
        courseAnnouncments.add(courseAnnouncment);
        when(courseAnnouncmentRepository.findAllByCourseField_Id(1L)).thenReturn(courseAnnouncments);

        List<CourseAnnouncmentDTO> courseAnnouncmentDTOS = this.courseAnnouncmentService.announcmentsOfACourse(1L);

        verify(courseAnnouncmentRepository, times(1)).findAllByCourseField_Id(1L);

        assertNotNull(courseAnnouncmentDTOS);
        assertTrue(courseAnnouncmentDTOS.size() == 1);
        assertTrue(courseAnnouncmentDTOS.get(0).getContent().equals(courseAnnouncment.getContent()));
    }

    @Test
    void testGetAllCommentsInACommentableAndLikeable() {
        Comment comment = new Comment();
        comment.setContent("Kjo eshte permbajtjat");
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        when(commentRepository.findAllByCommentedContent_IdOrderByUpdatedAtDesc(1L)).thenReturn(comments);

        List<CommentDTO> commentDTOS = this.commentService.getAllCommentsInACommentableAndLikeable(1L);

        verify(commentRepository, times(1)).findAllByCommentedContent_IdOrderByUpdatedAtDesc(1L);

        assertNotNull(commentDTOS);
        assertTrue(comments.size() == 1);
        assertTrue(comments.get(0).getContent().equals(comment.getContent()));



    }

    @Test
    void testAddCommentToACommentableAndLikeableException() {
        Comment comment = new Comment();
        comment.setContent("");

        assertThrows(ContentEmptyException.class, () -> this.commentService.addCommentToACommentableAndLikeable(1L, "", 1L));
    }

    @Test
    void testFindAllByStudentEnrolled_idPageable() {
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setName("Course " + i);
            courses.add(course);
        }

        when(courseRepository.findAllByStudentsEnrolled_Id(1L, PageRequest.of(0, 10))).thenReturn(courses);

        List<CourseDTO> courseDTOS = this.courseService.findAllByStudentEnrolled_id(1L, 0);

        assertNotNull(courseDTOS);
        assertTrue(courseDTOS.size() == 10);
    }

    @Test
    void testFindAllByStudentEnrolled_id() {
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            Course course = new Course();
            course.setName("Course " + i);
            courses.add(course);
        }

        when(courseRepository.findAllByStudentsEnrolled_Id(1L)).thenReturn(courses);

        List<CourseDTO> courseDTOS = this.courseService.findAllByStudentEnrolled_id(1L);

        assertNotNull(courseDTOS);
        assertTrue(courseDTOS.size() == 12);
    }

    @Test
    void testFindAllCoursesAvailableForLoggedUser() {
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            Course course = new Course();
            course.setName("Course " + i);
            courses.add(course);
        }

        when(courseRepository.findAllAvailableCourses(1L, PageRequest.of(0, 10))).thenReturn(courses);

        List<CourseDTO> courseDTOS = this.courseService.findAllCoursesAvailableForLoggedUser(1L, 0);

        assertNotNull(courseDTOS);
        assertTrue(courseDTOS.size() == 12);
    }

}
