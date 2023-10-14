package com.neyesem.neyesembackend.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public record CommentRequest(


        Long userId,
        Long restaurantId,

        @NotBlank(message = "Comment cannot be blank")
        String comment
) {
}
