package com.neyesem.neyesembackend.controller;

import com.neyesem.neyesembackend.dto.UserProfileResponse;
import com.neyesem.neyesembackend.dto.UserSearchResponse;
import com.neyesem.neyesembackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id){

        return new ResponseEntity<>(userService.getUserProfile(id), HttpStatus.OK);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserSearchResponse>> searchUsers(@RequestParam String username){

        return new ResponseEntity<>(userService.searchUserByUsername(username), HttpStatus.OK);

    }

}
