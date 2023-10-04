package com.neyesem.neyesembackend.repository;

import com.neyesem.neyesembackend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant,Long> {

    public Restaurant getRestaurantById(Long id);

}
