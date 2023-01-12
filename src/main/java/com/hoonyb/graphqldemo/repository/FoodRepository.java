package com.hoonyb.graphqldemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import com.hoonyb.graphqldemo.entity.Food;

@GraphQlRepository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByName(String name);
    
}
