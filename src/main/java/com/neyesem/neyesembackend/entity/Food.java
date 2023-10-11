package com.neyesem.neyesembackend.entity;


import com.neyesem.neyesembackend.dto.RestaurantFoodResponse;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
