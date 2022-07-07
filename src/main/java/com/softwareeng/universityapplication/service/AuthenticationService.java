package com.softwareeng.universityapplication.service;

import com.softwareeng.universityapplication.model.AuthenticationRequest;
import com.softwareeng.universityapplication.model.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
