package com.neyesem.neyesembackend.dto;

public record RestaurantCommentResponse(
        Long id,

        Long userId,

        String comment
) {
}
