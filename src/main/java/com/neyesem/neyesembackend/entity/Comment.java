package com.neyesem.neyesembackend.entity;


import com.neyesem.neyesembackend.dto.CommentRequest;
import com.neyesem.neyesembackend.dto.CommentResponse;
import com.neyesem.neyesembackend.dto.RestaurantCommentResponse;
import com.neyesem.neyesembackend.dto.UserCommentResponse;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String comment;


    @ManyToOne(targetEntity = Restaurant.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public CommentResponse entityToDto(){

        return new CommentResponse(
                this.id,
                this.comment,
                this.getRestaurant().entityToDto()
        );
    }

    public UserCommentResponse entityToUserCommentResponse(){

        return new UserCommentResponse(
                this.id,
                this.restaurant.getId(),
                this.comment
        );
    }

    public RestaurantCommentResponse entityToRestaurantCommentResponse(){

        return new RestaurantCommentResponse(
                this.id,
                this.user.getId(),
                this.comment
        );
    }


    public Comment() {
    }

    public Comment(Long id, String comment, Restaurant restaurant, User user) {
        this.id = id;
        this.comment = comment;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Comment(String comment, Restaurant restaurant, User user) {
        this.comment = comment;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(id, comment1.id) && Objects.equals(comment, comment1.comment) && Objects.equals(restaurant, comment1.restaurant) && Objects.equals(user, comment1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, restaurant, user);
    }
}
