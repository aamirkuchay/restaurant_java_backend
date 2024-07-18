package com.developer.zomato.controller;


import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.entity.User;
import com.developer.zomato.request.CreateRestaurantRequest;
import com.developer.zomato.service.RestaurantService;
import com.developer.zomato.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt,
                                                             @RequestParam String keyword) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    }


    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt
                                                            ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String jwt,
                                                              @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);

    }


    @PutMapping("/{id}/add-favorite")
    public ResponseEntity<Restaurant> addToFavorites(@RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
       Restaurant restaurant = restaurantService.addToFavorites(id,user);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);

    }

}
