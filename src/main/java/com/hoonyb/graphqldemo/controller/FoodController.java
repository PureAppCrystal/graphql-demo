package com.hoonyb.graphqldemo.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.hoonyb.graphqldemo.entity.Food;
import com.hoonyb.graphqldemo.service.FoodService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FoodController {
    
    private final FoodService foodService;


    /**
     * MutationMapping은 @PostMapping과 같은 어노테이션으로 graphql에 Mutation에 사용된다.
     * graphql은 엔드포인트와 하나이므로 @MuataionMapping만 지정해 주고 다른 설정은 필요 없다.
     * @param name
     * @return
     */
    @MutationMapping
    public Food save(@Argument String name) { // @Argumeent는 RequestBody/Param 과 같은 인자값을 지정해줄 때 사용한다.
        return foodService.save(name);
    }


    /**
     * get 과 비슷
     * @param name
     * @return
     */
    @QueryMapping
    public Food getFood(@Argument String name) {
        return foodService.getFood(name);
    }


    @QueryMapping
    public List<Food> getFoods(@Argument String name) {
        return foodService.getFoods();
    }
    
    
}
