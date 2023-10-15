package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.dto.CommentRequest;
import com.neyesem.neyesembackend.dto.CommentResponse;
import com.neyesem.neyesembackend.dto.RestaurantResponse;
import com.neyesem.neyesembackend.dto.Role;
import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.entity.Restaurant;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.repository.ICommentRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private ICommentRepository commentRepository;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void postComment_ValidRequest_itShouldReturnCommentResponse() {
        CommentRequest request = new CommentRequest(
                1L,
                1L,
                "comment"
        );

        Restaurant restaurant = new Restaurant(1L,"name","googleMapsLink", "address");
        User user = new User(1L,"username","email","firstName","lastname");
        Comment comment = new Comment("comment",restaurant,user);
        Comment comment2 = new Comment(1L,"comment",restaurant,user);

        CommentResponse expected = new CommentResponse(1L,"comment",restaurant.entityToDto());

        when(restaurantService.findRestaurantById(request.restaurantId())).thenReturn(restaurant);
        when(userService.findUserById(request.userId())).thenReturn(user);
        when(commentRepository.save(comment)).thenReturn(comment2);

        CommentResponse result = commentService.postComment(request);

        assertEquals(result, expected);


    }



    @Test
    public void getCommentByRestaurantId_ValidRequest_itShouldReturnListCommentResponse(){
        Restaurant restaurant = new Restaurant(1L,"name","googleMapsLink", "address");
        User user = new User(1L,"username","email","firstName","lastname");
        Comment comment = new Comment("comment",restaurant,user);
        Comment comment2 = new Comment(1L,"comment",restaurant,user);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment2);

        List<CommentResponse> expected = new ArrayList<>();
        expected.add(comment.entityToDto());
        expected.add(comment2.entityToDto());

        when(commentRepository.getCommentByRestaurantId(1L)).thenReturn(comments);

        List<CommentResponse> result = commentService.getCommentByRestaurantId(1L);

        assertEquals(result, expected);
    }
}