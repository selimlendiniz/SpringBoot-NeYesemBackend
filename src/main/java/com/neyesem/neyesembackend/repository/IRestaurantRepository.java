package com.neyesem.neyesembackend.repository;

import com.neyesem.neyesembackend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> getRestaurantById(Long id);

    List<Restaurant> getRestaurantsByNameContainingIgnoreCase(String name);


}
