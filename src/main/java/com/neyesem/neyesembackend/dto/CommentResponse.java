package com.neyesem.neyesembackend.dto;

public record CommentResponse(
        Long id,
        String comment,
        RestaurantResponse restaurant


) {
}
