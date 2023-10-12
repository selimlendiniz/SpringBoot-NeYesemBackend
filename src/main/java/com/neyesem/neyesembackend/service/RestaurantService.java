package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.dto.RestaurantProfileResponse;
import com.neyesem.neyesembackend.dto.RestaurantSearchResponse;
import com.neyesem.neyesembackend.dto.UserProfileResponse;
import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.entity.Food;
import com.neyesem.neyesembackend.entity.Restaurant;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.exception.EntityNotFoundException;
import com.neyesem.neyesembackend.repository.IRestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RestaurantService {

    private final IRestaurantRepository restaurantRepository;

    public RestaurantService(IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public void deleteRestaurantById(Long id){

        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Restaurant",id);
        }

    }



    public Restaurant findRestaurantById(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        if (!restaurant.isPresent()){
            throw new EntityNotFoundException("Restaurant",id);
        }

        return restaurant.get();
    }

    public Restaurant saveRestaurant(Restaurant newRestaurant){
        newRestaurant.setCreateDate(new Date(System.currentTimeMillis()));
        return restaurantRepository.save(newRestaurant);
    }


    public Restaurant updateRestaurantById(Long id, Restaurant updateRestaurant) {

        Restaurant oldRestaurant = findRestaurantById(id);
        oldRestaurant.setAddress(updateRestaurant.getAddress());
        oldRestaurant.setGoogleMapsLink(updateRestaurant.getGoogleMapsLink());
        return restaurantRepository.save(oldRestaurant);

    }

    public RestaurantProfileResponse getUserProfile(Long id){

        Restaurant restaurant = restaurantRepository.getRestaurantById(id).orElseThrow(() -> new EntityNotFoundException("User",id));

        return restaurant.entityToRestaurantProfileResponse();

    }

    public List<RestaurantSearchResponse>  searchRestaurants(String name){
        return restaurantRepository.getRestaurantsByNameContainingIgnoreCase(name)
                .stream()
                .map(Restaurant::entityToRestaurantSearchResponse)
                .collect(Collectors.toList());
    }


}
