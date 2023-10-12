package com.neyesem.neyesembackend.controller;

import com.neyesem.neyesembackend.dto.RestaurantProfileResponse;
import com.neyesem.neyesembackend.entity.Restaurant;
import com.neyesem.neyesembackend.service.RestaurantService;
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
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        System.out.println("selim");
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantProfileResponse> getProfile(@PathVariable Long id){
        return new ResponseEntity<>(restaurantService.getUserProfile(id) , HttpStatus.OK);
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

    @PostMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updateRestaurant){
        return new ResponseEntity<>(restaurantService.updateRestaurantById(id,updateRestaurant),HttpStatus.OK);

    }



}
