package com.neyesem.neyesembackend.dto;

import java.util.List;

public record RestaurantProfileResponse(
        Long id,

        String name,

        String googleMapsLink,

        String address,

        List<RestaurantCommentResponse> comments,

        List<RestaurantFoodResponse> foods

) {
}
