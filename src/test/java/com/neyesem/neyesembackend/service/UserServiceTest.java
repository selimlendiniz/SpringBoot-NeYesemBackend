package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.dto.Role;
import com.neyesem.neyesembackend.dto.UserCommentResponse;
import com.neyesem.neyesembackend.dto.UserProfileResponse;
import com.neyesem.neyesembackend.dto.UserSearchResponse;
import com.neyesem.neyesembackend.entity.*;
import com.neyesem.neyesembackend.exception.UserNotFoundException;
import com.neyesem.neyesembackend.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getUserProfile method should return UserProfileResponse")
    public void getUserProfile_ValidRequest_itShouldReturnUserProfileResponse() {

        // Given
        Long id = 1L;

        Comment comment = new Comment(
                1L,
                "comment",
                new Restaurant(),
                new User()
        );

        Token token = new Token(
                new User(),
                "token",
                TokenType.BEARER,
                false,
                false
        );

        User user = new User(
                1L,
                "username",
                "email",
                "password",
                Role.USER,
                "firstName",
                "lastName",
                List.of(comment,comment),
                List.of(token,token)
        );



        UserProfileResponse expected = new UserProfileResponse(
                1L,
                "username",
                "firstName",
                "lastName",
                List.of(comment.entityToUserCommentResponse(),comment.entityToUserCommentResponse())
        );


        // When
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Then
        UserProfileResponse result = userService.getUserProfile(id);

        // Assert
        assertEquals(result,expected);

        //Verify
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("getUserProfile method should throw UserNotFoundException")
    public void getUserProfile_InvalidRequest_itShouldThrowUserNotFoundException() {

        // Given
        Long id = 1L;

        // When
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> userService.getUserProfile(id))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User not found with id: " + id);

        //Verify
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);

    }

    @Test
    @DisplayName("searchUserByUsername method should return List<UserSearchResponse>")
    public void searchUserByUsername_ValidRequest_itShouldReturnListUserSearchResponse() {

        // Given
        String username = "username";

        User user = new User(
                1L,
                "username",
                "email",
                "password",
                Role.USER,
                "firstName",
                "lastName",
                null,
                null
        );

        List<UserSearchResponse> expected = List.of(
                new UserSearchResponse(
                        1L,
                        "username"
                )
        );

        // When
        Mockito.when(userRepository.findUsersByUsernameContainsIgnoreCase(username)).thenReturn(List.of(user));

        // Then
        List<UserSearchResponse> result = userService.searchUserByUsername(username);

        // Assert
        assertEquals(result,expected);

        //Verify
        Mockito.verify(userRepository, Mockito.times(1)).findUsersByUsernameContainsIgnoreCase(username);

    }

}
