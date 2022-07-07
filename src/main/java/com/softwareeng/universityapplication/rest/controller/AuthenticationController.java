package com.softwareeng.universityapplication.rest.controller;

import com.softwareeng.universityapplication.model.AuthenticationRequest;
import com.softwareeng.universityapplication.model.AuthenticationResponse;
import com.softwareeng.universityapplication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> loginRequest(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(service.authenticate(authenticationRequest));
    }
}
