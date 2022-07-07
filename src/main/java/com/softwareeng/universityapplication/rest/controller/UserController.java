package com.softwareeng.universityapplication.rest.controller;

import com.softwareeng.universityapplication.rest.controller.common.CommonCrudRestController;
import com.softwareeng.universityapplication.userDetails.MyUserDetails;
import com.softwareeng.universityapplication.businessLogic.dtos.UserDTO;
import com.softwareeng.universityapplication.businessLogic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends CommonCrudRestController<UserDTO, Long> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Void> createObject(@RequestBody UserDTO dto) throws URISyntaxException {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return super.createObject(dto);
    }

    @PostMapping("/email")
    public UserDTO findUserByEmail(@RequestBody String email) {
        return ((UserService) service).findByEmail(email);
    }

    @GetMapping("/courseEnrolled/{idCourse}")
    public ResponseEntity<List<UserDTO>> getAllStudentsEnrolledInACourse(@PathVariable("idCourse") Long idCourse) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(((UserService) service).getUsersEnrolledInACourse(idCourse, userDetails.getId()));
    }

    @GetMapping("/friends/{pageNumber}")
    public ResponseEntity<List<UserDTO>> getFriendsOfLoggedUser(@PathVariable("pageNumber") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(((UserService) service).getFriendsOfAUser(userDetails.getId(), pageNumber));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getLoggedUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(((UserService) service).findById(userDetails.getId()));
    }


}
