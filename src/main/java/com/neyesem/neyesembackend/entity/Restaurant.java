package com.neyesem.neyesembackend.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "restaurant")
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


    public Restaurant() {
    }

    public Restaurant(Long id, String name, String googleMapsLink, Date createDate, String address) {
        this.id = id;
        this.name = name;
        this.googleMapsLink = googleMapsLink;
        this.createDate = createDate;
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
