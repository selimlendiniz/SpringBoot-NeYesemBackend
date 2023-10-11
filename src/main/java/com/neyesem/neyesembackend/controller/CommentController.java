package com.neyesem.neyesembackend.controller;

import com.neyesem.neyesembackend.dto.CommentRequest;
import com.neyesem.neyesembackend.dto.CommentResponse;
import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/restaurant/{id}")
    public ResponseEntity<List<CommentResponse>> getCommentById(@PathVariable Long id) {

        return new ResponseEntity<>(commentService.getCommentByRestaurantId(id),HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> postComment(@RequestBody CommentRequest commentRequest) {

        System.out.println(commentRequest);

        return new ResponseEntity<>( commentService.postComment(commentRequest), HttpStatus.OK);
    }


}
