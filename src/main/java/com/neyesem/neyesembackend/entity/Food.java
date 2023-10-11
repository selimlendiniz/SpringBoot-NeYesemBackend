package com.neyesem.neyesembackend.entity;


import com.neyesem.neyesembackend.dto.RestaurantFoodResponse;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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

    public RestaurantFoodResponse entityToRestaurantFoodResponse(){
        return new RestaurantFoodResponse(
                this.id,
                this.name

        );
    }

}
