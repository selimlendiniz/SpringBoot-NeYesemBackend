package com.neyesem.neyesembackend.repository;

import com.neyesem.neyesembackend.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends JpaRepository<Food,Long> {
}
