package com.neyesem.neyesembackend.dto;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String firstname,
        String lastname

) {
}
