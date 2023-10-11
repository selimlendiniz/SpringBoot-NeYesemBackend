package com.neyesem.neyesembackend.entity;


import com.neyesem.neyesembackend.dto.RestaurantDetailResponse;
import com.neyesem.neyesembackend.dto.RestaurantResponse;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "restaurants")
public class Restaurant {


    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "google_maps_link")
    private String googleMapsLink;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "address")
    private String address;

    @ManyToMany(targetEntity = Food.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id",referencedColumnName = "id")
    private List<Food> foods;


    public RestaurantResponse entityToDto(){

        return new RestaurantResponse(

                this.id,
                this.name,
                this.googleMapsLink,
                this.address
        );
    }

    public RestaurantDetailResponse entityToRestaurantDetailResponse(){
        return new RestaurantDetailResponse(
                this.id,
                this.name,
                this.googleMapsLink,
                this.address,
                this.foods
                        .stream()
                        .map(Food::entityToRestaurantFoodResponse)
                        .collect(Collectors.toList())
        );
    }


    public Restaurant() {
    }

    public Restaurant(Long id, String name, String googleMapsLink, Date createDate, String address) {
        this.id = id;
        this.name = name;
        this.googleMapsLink = googleMapsLink;
        this.createDate = createDate;
        this.address = address;
    }

    public Restaurant(Long id, String name, String googleMapsLink, String address) {
        this.id = id;
        this.name = name;
        this.googleMapsLink = googleMapsLink;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoogleMapsLink() {
        return googleMapsLink;
    }

    public void setGoogleMapsLink(String googleMapsLink) {
        this.googleMapsLink = googleMapsLink;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(googleMapsLink, that.googleMapsLink) && Objects.equals(createDate, that.createDate) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, googleMapsLink, createDate, address);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", googleMapsLink='" + googleMapsLink + '\'' +
                ", createDate=" + createDate +
                ", address='" + address + '\'' +
                '}';
    }
}
