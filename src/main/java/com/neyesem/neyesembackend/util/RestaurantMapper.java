package com.neyesem.neyesembackend.util;

import com.neyesem.neyesembackend.dto.RestaurantResponse;
import com.neyesem.neyesembackend.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RestaurantMapper implements Function<Restaurant,RestaurantResponse> {


    @Override
    public RestaurantResponse apply(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getGoogleMapsLink(),
                restaurant.getAddress()
        );
    }
}
