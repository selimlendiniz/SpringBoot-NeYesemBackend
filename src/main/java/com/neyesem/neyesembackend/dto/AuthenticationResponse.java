package com.neyesem.neyesembackend.dto;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
