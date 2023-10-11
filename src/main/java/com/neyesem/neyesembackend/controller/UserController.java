package com.neyesem.neyesembackend.controller;

import com.neyesem.neyesembackend.dto.UserProfileResponse;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.service.UserCommentService;
import com.neyesem.neyesembackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final UserCommentService userCommentService;

    public UserController(UserService userService, UserCommentService userCommentService) {
        this.userService = userService;
        this.userCommentService = userCommentService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id){

        return new ResponseEntity<>(userCommentService.getUserProfile(id), HttpStatus.OK);

    }

}
