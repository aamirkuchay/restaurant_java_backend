package com.developer.zomato.controller;


import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.entity.User;
import com.developer.zomato.request.CreateRestaurantRequest;
import com.developer.zomato.response.MessageResponse;
import com.developer.zomato.service.RestaurantService;
import com.developer.zomato.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<Restaurant>createRestaurant(@RequestBody CreateRestaurantRequest req,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant>updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                                      @RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id,req);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>deleteRestaurant(
                                                      @RequestHeader("Authorization") String jwt,
                                                      @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
       restaurantService.deleteRestaurant(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Restaurant Deleted Successfully...");
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant>updateRestaurantStatus(
                                                           @RequestHeader("Authorization") String jwt,
                                                           @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
        return  ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant>findRestaurantByUserId(
                                                            @RequestHeader("Authorization") String jwt
                                                            ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        return  ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    }



}
