package com.neyesem.neyesembackend.controller;

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
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@RequestParam Long id){
        return new ResponseEntity<>(restaurantService.getRestaurantById(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createBlog(@RequestBody Restaurant newBlog){
        return new ResponseEntity<>(restaurantService.saveRestaurant(newBlog),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id){
        restaurantService.deleteRestaurantById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Restaurant> updateBlog(@PathVariable Long id, @RequestBody Restaurant updateBlog){
        return new ResponseEntity<>(restaurantService.updateRestaurantById(id,updateBlog),HttpStatus.OK);

    }



}
