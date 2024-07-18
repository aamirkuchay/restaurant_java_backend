package com.developer.zomato.controller;

import com.developer.zomato.entity.Food;
import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.entity.User;
import com.developer.zomato.request.CreateFoodRequest;
import com.developer.zomato.response.MessageResponse;
import com.developer.zomato.service.FoodService;
import com.developer.zomato.service.RestaurantService;
import com.developer.zomato.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;


    @PostMapping
   public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req,req.getCategory(),restaurant);
        return new ResponseEntity<>(food,HttpStatus.CREATED);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Food Deleted Sucessfully..");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food,HttpStatus.OK);
    }



}
