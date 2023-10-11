package com.neyesem.neyesembackend.dto;

public record UserDetailResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName
) {
}
