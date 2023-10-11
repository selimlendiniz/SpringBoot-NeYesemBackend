package com.neyesem.neyesembackend.dto;

public record UserssCommentResponse(
        Long id,

        Long restaurantId,

        String comment
) {
}
