package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.config.JwtService;
import com.neyesem.neyesembackend.dto.AuthenticationRequest;
import com.neyesem.neyesembackend.dto.AuthenticationResponse;
import com.neyesem.neyesembackend.dto.RegisterRequest;
import com.neyesem.neyesembackend.dto.Role;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.exception.UserAlreadyExistsException;
import com.neyesem.neyesembackend.exception.UserNotFoundException;
import com.neyesem.neyesembackend.repository.ITokenRepository;
import com.neyesem.neyesembackend.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ITokenRepository tokenRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("register method should return AuthenticationResponse")
    void register_ValidRequest_itShouldReturnAuthenticationResponse() {

        RegisterRequest request = new RegisterRequest(
                "testUsername",
                "testEmail",
                "testPassword",
                "testFirstName",
                "testLastName"
        );

        User user = new User(
                null,
                "testUsername",
                "testEmail",
                null,
                Role.USER,
                "testFirstName",
                "testLastName",
                null,
                null
        );

        AuthenticationResponse expected = new AuthenticationResponse(
                "testAccessToken", "testRefreshToken");

        when(userService.findByUsername(request.username())).thenReturn(Optional.empty());
        when(userService.save(user)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("testAccessToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("testRefreshToken");

        //Call the testing method
        AuthenticationResponse result = authenticationService.register(request);

        //Check results
        assertEquals(expected, result);

        //Verify the mock methods are called
        Mockito.verify(userService).findByUsername(request.username());
        Mockito.verify(userService).save(user);
        Mockito.verify(jwtService).generateToken(user);
        Mockito.verify(jwtService).generateRefreshToken(user);


    }

    @Test
    @DisplayName("register method should throw UserAlreadyExistsException")
    void register_InvalidRequest_itShouldThrowUserAlreadyExistsException() {

        User user = new User(
                null,
                "testUsername",
                "testEmail",
                null,
                Role.USER,
                "testFirstName",
                "testLastName",
                null,
                null
        );

        RegisterRequest request = new RegisterRequest(
                "testUsername",
                "testEmail",
                "testPassword",
                "testFirstName",
                "testLastName"
        );

        when(userService.findByUsername(request.username())).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> authenticationService.register(request))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("user already exists with username: " + request.username());


        //Verify the mock methods are called
        Mockito.verify(userService).findByUsername(request.username());
        Mockito.verifyNoInteractions(jwtService);
    }

    @Test
    void authenticate_ValidRequest_itShouldReturnAuthenticationResponse() {

        AuthenticationRequest validRequest = new AuthenticationRequest(
                "testUsername",
                "testPassword"
        );

        AuthenticationResponse expected = new AuthenticationResponse(
                "testToken",
                "testRefreshToken"
        );

        User user = new User(
                1L,
                "testUsername",
                "testEmail",
                "testPassword",
                Role.USER,
                "testFirstName",
                "testLastName",
                null,
                null
        );

        when(userService.findByUsername(validRequest.username())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("testToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("testRefreshToken");

        //Call the testing method
        AuthenticationResponse result = authenticationService.authenticate(validRequest);

        //Check results
        assertEquals(expected, result);

        //Verify the mock methods are called
        Mockito.verify(userService).findByUsername(validRequest.username());
        Mockito.verify(jwtService).generateToken(any(User.class));
        Mockito.verify(jwtService).generateRefreshToken(any(User.class));
    }

    @Test
    void authenticate_InvalidRequestIfUserNotFound_itShouldThrowUserNotFoundException() {

        AuthenticationRequest invalidRequest = new AuthenticationRequest(
                "testUsername",
                "testPassword"
        );

        when(userService.findByUsername(invalidRequest.username())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authenticationService.authenticate(invalidRequest))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found with username: " + invalidRequest.username());

        //Verify the mock methods are called
        Mockito.verify(userService).findByUsername(invalidRequest.username());
        Mockito.verifyNoInteractions(jwtService);

    }


}