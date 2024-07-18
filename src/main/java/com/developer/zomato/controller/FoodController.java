package com.developer.zomato.controller;


import com.developer.zomato.entity.Food;
import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.entity.User;
import com.developer.zomato.request.CreateFoodRequest;
import com.developer.zomato.service.FoodService;
import com.developer.zomato.service.RestaurantService;
import com.developer.zomato.service.UserService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity <List<Food>> searchFood(@RequestParam String keyword,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
      @RequestParam boolean vegetarian,
      @RequestParam boolean seasonal,
      @RequestParam boolean nonVeg,
      @RequestParam(required = false) String food_category,
      @RequestParam Long restaurantId , @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantFood(restaurantId,vegetarian,seasonal,nonVeg,food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
