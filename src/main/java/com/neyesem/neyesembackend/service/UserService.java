package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.exception.EntityNotFoundException;
import com.neyesem.neyesembackend.repository.IUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


}
