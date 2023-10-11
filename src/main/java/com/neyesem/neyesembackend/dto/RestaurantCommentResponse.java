package com.neyesem.neyesembackend.dto;

import com.neyesem.neyesembackend.entity.Comment;

public record RestaurantCommentResponse(
        Long id,

        Long userId,

        String comment
) {
}
