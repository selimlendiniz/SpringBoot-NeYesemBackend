package com.neyesem.neyesembackend.controller;

import com.neyesem.neyesembackend.dto.RestaurantProfileResponse;
import com.neyesem.neyesembackend.dto.RestaurantSearchResponse;
import com.neyesem.neyesembackend.entity.Restaurant;
import com.neyesem.neyesembackend.service.RestaurantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {


    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantSearchResponse>> searchRestaurants(@RequestParam String name){

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name parameter cannot be empty");
        }

        return new ResponseEntity<>(restaurantService.searchRestaurants(name),HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantProfileResponse> getProfile(@PathVariable Long id){
        return new ResponseEntity<>(restaurantService.getRestaurantProfile(id) , HttpStatus.OK);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant newRestaurant){
        return new ResponseEntity<>(restaurantService.saveRestaurant(newRestaurant),HttpStatus.CREATED);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurantById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
