package com.neyesem.neyesembackend.dto;

public record RestaurantResponse(
        Long id,
        String name,
        String googleMapsLink,
        String address
) {
}
