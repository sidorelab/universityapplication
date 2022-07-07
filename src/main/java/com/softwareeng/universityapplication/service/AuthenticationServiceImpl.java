package com.softwareeng.universityapplication.service;

import com.softwareeng.universityapplication.JwtUtil;
import com.softwareeng.universityapplication.exceptions.MyBadCredentialsException;
import com.softwareeng.universityapplication.model.AuthenticationRequest;
import com.softwareeng.universityapplication.model.AuthenticationResponse;
import com.softwareeng.universityapplication.userDetails.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtUtil.generateToken((MyUserDetails) userDetails));
            return authenticationResponse;
        } catch(BadCredentialsException e) {
            throw new MyBadCredentialsException();
        }
    }
}
