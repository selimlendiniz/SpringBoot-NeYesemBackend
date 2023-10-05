package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.config.JwtService;
import com.neyesem.neyesembackend.dto.AuthenticationRequest;
import com.neyesem.neyesembackend.dto.AuthenticationResponse;
import com.neyesem.neyesembackend.dto.RegisterRequest;
import com.neyesem.neyesembackend.dto.Role;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.repository.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        if (!userService.findByEmail(request.email()).isEmpty()){
            throw new RuntimeException("Bu email adresi ile zaten bir kullanici mevcut");
        }

        if (!userService.findByUsername(request.username()).isEmpty()){
            throw new RuntimeException("Bu email adresi ile zaten bir kullanici mevcut");
        }



        User user = new User(
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password()),
                Role.USER,
                request.firstname(),
                request.lastname()

        );

        userService.save(user);

        var jwtToken = jwtService.generateToken(user);


        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
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
