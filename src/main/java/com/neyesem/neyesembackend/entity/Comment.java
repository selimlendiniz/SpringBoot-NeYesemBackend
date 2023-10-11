package com.neyesem.neyesembackend.entity;


import com.neyesem.neyesembackend.dto.CommentResponse;
import com.neyesem.neyesembackend.dto.UserssCommentResponse;
import jakarta.persistence.*;

import java.util.List;

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

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public CommentResponse entityToDto(){

        return new CommentResponse(
                this.id,
                this.comment,
                this.getRestaurant().entityToDto(),
                this.getUser().entityToDto()
        );
    }

    public UserssCommentResponse entityToUserssCommentResponse(){

        return new UserssCommentResponse(
                this.id,
                this.getRestaurant().getId(),
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
}
