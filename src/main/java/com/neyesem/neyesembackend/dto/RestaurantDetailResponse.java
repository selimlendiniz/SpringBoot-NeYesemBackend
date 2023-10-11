package com.neyesem.neyesembackend.dto;

import com.neyesem.neyesembackend.entity.User;

import java.util.List;

public record RestaurantDetailResponse(
        Long id,

        String name,

        String googleMapsLink,

        String address,


        List<RestaurantFoodResponse> foods

) {
}
