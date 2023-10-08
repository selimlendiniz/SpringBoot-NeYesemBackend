package com.neyesem.neyesembackend.util;

import com.neyesem.neyesembackend.dto.UserResponse;
import com.neyesem.neyesembackend.entity.User;

public class UserMapper {
    public static UserResponse entityToDto(User entity) {
        UserResponse dto = new UserResponse(

                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName()
        );

        return dto;
    }

    public static User dtoToEntity(UserResponse dto) {
        User entity = new User(
                dto.id(),
                dto.username(),
                dto.email(),
                dto.firstName(),
                dto.lastName()
        );

        return entity;
    }
}
