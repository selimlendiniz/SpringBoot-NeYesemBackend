package com.neyesem.neyesembackend.service;

import com.neyesem.neyesembackend.dto.RestaurantProfileResponse;
import com.neyesem.neyesembackend.dto.RestaurantResponse;
import com.neyesem.neyesembackend.dto.RestaurantSearchResponse;
import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.entity.Food;
import com.neyesem.neyesembackend.entity.Restaurant;
import com.neyesem.neyesembackend.entity.User;
import com.neyesem.neyesembackend.exception.RestaurantNotFoundException;
import com.neyesem.neyesembackend.exception.UserNotFoundException;
import com.neyesem.neyesembackend.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private IRestaurantRepository restaurantRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findRestaurantById_ValidRequest_itShouldReturnRestaurant() {
        Long id = 1L;

        Restaurant restaurant = new Restaurant(1L,"name","googleMapsLink","address");

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));

        Restaurant result = restaurantService.findRestaurantById(id);

        assertEquals(restaurant,result);
    }

    @Test
    public void findRestaurantById_InvalidRequest_itShouldThrowRestaurantNotFoundException() {
        Long id = 1L;

        when(restaurantRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> restaurantService.findRestaurantById(id))
                .isInstanceOf(RestaurantNotFoundException.class)
                .hasMessage("Restaurant not found with id: " + id);


        Mockito.verify(restaurantRepository,Mockito.times(1)).findById(id);


    }

    @Test
    public void getRestaurantProfile_ValidRequest_itShouldReturnRestaurantProfileResponse() {

        Long id = 1L;

        User user = new User(1L,"username","password","firstName","lastName");
        Restaurant restaurant = new Restaurant(1L,"name","googleMapsLink","address");
        Comment comment = new Comment(1L,"comment",restaurant,user);


        Restaurant restaurant2 = new Restaurant(1L,"name","googleMapsLink",new Date(),"address",List.of(comment), Set.of(new Food(1L,"name")));

        when(restaurantRepository.getRestaurantById(id)).thenReturn(Optional.of(restaurant2));

        RestaurantProfileResponse result = restaurantService.getRestaurantProfile(id);

        assertEquals(restaurant2.entityToRestaurantProfileResponse(),result);

        Mockito.verify(restaurantRepository,Mockito.times(1)).getRestaurantById(id);



    }

    @Test
    public void getRestaurantProfile_InvalidRequest_itShouldThrowRestaurantNotFoundException() {

        Long id = 1L;

        when(restaurantRepository.getRestaurantById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> restaurantService.getRestaurantProfile(id))
                .isInstanceOf(RestaurantNotFoundException.class)
                .hasMessage("Restaurant not found with id: " + id);


        Mockito.verify(restaurantRepository,Mockito.times(1)).getRestaurantById(id);

    }

    @Test
    void searchRestaurants_ValidRequest_itShouldReturnRestaurantSearchResponse() {

        String name = "name";

        Restaurant restaurant = new Restaurant(1L,"name","googleMapsLink","address");

        when(restaurantRepository.getRestaurantsByNameContainingIgnoreCase(name)).thenReturn(List.of(restaurant));

        List<RestaurantSearchResponse> result = restaurantService.searchRestaurants(name);

        assertEquals(List.of(restaurant.entityToRestaurantSearchResponse()),result);

        Mockito.verify(restaurantRepository,Mockito.times(1)).getRestaurantsByNameContainingIgnoreCase(name);


    }


}