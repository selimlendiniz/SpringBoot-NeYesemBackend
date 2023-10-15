package com.neyesem.neyesembackend.entity;


import com.neyesem.neyesembackend.dto.RestaurantFoodResponse;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "foods")
public class Food {


    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToMany(mappedBy = "foods")
    private Set<Restaurant> restaurants = new HashSet<>();


    public RestaurantFoodResponse entityToRestaurantFoodResponse(){
        return new RestaurantFoodResponse(
                this.id,
                this.name
        );
    }

    public Food(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Food() {
    }

    public Food(Long id, String name, Date createDate, Set<Restaurant> restaurants) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.restaurants = restaurants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id) && Objects.equals(name, food.name) && Objects.equals(createDate, food.createDate) && Objects.equals(restaurants, food.restaurants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createDate, restaurants);
    }
}
