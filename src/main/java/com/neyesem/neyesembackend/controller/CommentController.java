package com.neyesem.neyesembackend.controller;

import com.neyesem.neyesembackend.dto.CommentRequest;
import com.neyesem.neyesembackend.dto.CommentResponse;
import com.neyesem.neyesembackend.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> getCommentById(@RequestParam Long restaurantId) {

        return new ResponseEntity<>(commentService.getCommentByRestaurantId(restaurantId), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> postComment(@RequestBody @Valid CommentRequest commentRequest) {

        System.out.println(commentRequest);

        return new ResponseEntity<>(commentService.postComment(commentRequest), HttpStatus.OK);
    }


}
