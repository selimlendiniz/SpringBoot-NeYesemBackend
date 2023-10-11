package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.dto.UserProfileResponse;
import com.neyesem.neyesembackend.dto.UserSearchResponse;
import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.exception.EntityNotFoundException;
import com.neyesem.neyesembackend.repository.IUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User newUser){
        userRepository.save(newUser);
    }

    public Optional<User> findByUsername(String username){

        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return  userRepository.findByEmail(email);
    }

    public User findById(Long id){

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User",id);
        }

        return user.get();
    }


    public UserProfileResponse getUserProfile(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User",id));

        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getComments()
                        .stream()
                        .map(Comment::entityToUserCommentResponse)
                        .collect(Collectors.toList())
        );

    }

    public List<UserSearchResponse> searchUserByUsername(String username){

        return userRepository.findUsersByUsernameContainsIgnoreCase(username)
                .stream()
                .map(User::entityToUserSearchResponse)
                .collect(Collectors.toList());
    }


}
