package com.neyesem.neyesembackend.dto;

public record UserCommentResponse(
        Long id,

        Long userId,

        String comment
) {
}
