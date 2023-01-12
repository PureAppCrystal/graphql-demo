package com.hoonyb.graphqldemo.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoonyb.graphqldemo.entity.Food;
import com.hoonyb.graphqldemo.repository.FoodRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {
    
    private final FoodRepository foodRepository;

    public Food save(String name) {
        return foodRepository.save(Food.from(name));
    }

    public Food getFood(String name) {
        return foodRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    public List<Food> getFoods() {
        return foodRepository.findAll();
    }
}
