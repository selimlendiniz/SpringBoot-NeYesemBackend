package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.dto.CommentRequest;
import com.neyesem.neyesembackend.dto.CommentResponse;
import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.repository.ICommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {


    private final ICommentRepository commentRepository;

    private final RestaurantService restaurantService;

    private final UserService userService;


    public CommentService(ICommentRepository commentRepository, RestaurantService restaurantService, UserService userService) {
        this.commentRepository = commentRepository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public CommentResponse postComment(CommentRequest commentRequest) {

        Comment comment = new Comment(
                commentRequest.comment(),
                restaurantService.findRestaurantById(commentRequest.restaurantId()),
                userService.findUserById(commentRequest.userId()));

        return commentRepository.save(comment).entityToDto();
    }

    public List<CommentResponse> getCommentByRestaurantId(Long id) {

        List<CommentResponse> comments = commentRepository
                .getCommentByRestaurantId(id)
                .stream()
                .map(Comment::entityToDto)
                .collect(Collectors.toList());

        return comments;
    }

}
