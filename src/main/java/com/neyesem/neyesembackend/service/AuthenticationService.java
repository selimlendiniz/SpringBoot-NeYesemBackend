package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.config.JwtService;
import com.neyesem.neyesembackend.dto.AuthenticationRequest;
import com.neyesem.neyesembackend.dto.AuthenticationResponse;
import com.neyesem.neyesembackend.dto.RegisterRequest;
import com.neyesem.neyesembackend.dto.Role;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.exception.EntityAlreadyExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse register(RegisterRequest request) {

        if (userService.findByEmail(request.email()).isPresent()){
            throw new EntityAlreadyExistsException("user already exists with email: " + request.email());
        }

        if (userService.findByUsername(request.username()).isPresent()){
            throw new EntityAlreadyExistsException("user already exists with username: " + request.username());
        }




        User user = new User(
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password()),
                Role.USER,
                request.firstName(),
                request.lastName()

        );

        userService.save(user);

        var jwtToken = jwtService.generateToken(user);


        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        userService.findByUsername(request.username()).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.username()));


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        var user = userService.findByUsername(request.username())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);


        return new AuthenticationResponse(jwtToken);
    }
}
