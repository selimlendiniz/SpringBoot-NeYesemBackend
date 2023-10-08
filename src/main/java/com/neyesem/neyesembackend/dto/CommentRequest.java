package com.neyesem.neyesembackend.dto;

public record CommentRequest(
        Long userId,
        Long restaurantId,
        String comment
) {
}
