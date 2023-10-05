package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.entity.Restaurant;
import com.neyesem.neyesembackend.repository.IRestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


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
        restaurantRepository.deleteById(id);
    }

    public void createRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }



    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    public Restaurant saveRestaurant(Restaurant newRestaurant){
        newRestaurant.setCreateDate(new Date(System.currentTimeMillis()));

        return restaurantRepository.save(newRestaurant);
    }


    public Restaurant updateRestaurantById(Long id, Restaurant updateRestaurant) {

        Restaurant oldRestaurant = restaurantRepository.getRestaurantById(id);
        oldRestaurant.setAddress(updateRestaurant.getAddress());
        oldRestaurant.setGoogleMapsLink(updateRestaurant.getGoogleMapsLink());



        return restaurantRepository.save(oldRestaurant);
    }
}
