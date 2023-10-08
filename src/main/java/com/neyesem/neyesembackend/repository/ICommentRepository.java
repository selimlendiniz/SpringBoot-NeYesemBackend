package com.neyesem.neyesembackend.repository;

import com.neyesem.neyesembackend.entity.Comment;
import com.neyesem.neyesembackend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getCommentByRestaurant(Restaurant restaurant);

    List<Comment> getCommentByRestaurantId(Long Restaurant);

}
