package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.dto.UserProfileResponse;
import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.exception.EntityNotFoundException;
import com.neyesem.neyesembackend.repository.ICommentRepository;
import com.neyesem.neyesembackend.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCommentService {

    private final ICommentRepository commentRepository;

    private final IUserRepository userRepository;


    public UserCommentService(ICommentRepository commentRepository, IUserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public UserProfileResponse getUserProfile(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User",id));
        List<Comment> comment = commentRepository.getCommentsByUserId(id);


        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                comment
                        .stream()
                        .map(Comment::entityToUserssCommentResponse)
                        .collect(Collectors.toList())
        );

    }

}
