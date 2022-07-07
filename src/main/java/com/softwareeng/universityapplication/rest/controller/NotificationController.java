package com.softwareeng.universityapplication.rest.controller;

import com.softwareeng.universityapplication.rest.controller.common.CommonCrudRestController;
import com.softwareeng.universityapplication.userDetails.MyUserDetails;
import com.softwareeng.universityapplication.businessLogic.dtos.NotificationDTO;
import com.softwareeng.universityapplication.businessLogic.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController extends CommonCrudRestController<NotificationDTO, Long> {

    @GetMapping("/currentUser")
    public ResponseEntity<List<NotificationDTO>> getNotificationsOfAUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(getNotificationService().getAllUnseenNotificationsOfAUser(userDetails.getId()));
    }

    @PutMapping("/see")
    public ResponseEntity<Void> seeNotification(@RequestBody Long idNotification) {
        getNotificationService().seeNotification(idNotification);
        return ResponseEntity.ok().build();
    }

    private NotificationService getNotificationService() {
        return (NotificationService) service;
    }
}
