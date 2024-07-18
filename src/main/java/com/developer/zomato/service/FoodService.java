package com.developer.zomato.service;

import com.developer.zomato.entity.Category;
import com.developer.zomato.entity.Food;
import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVeg, boolean isNonVeg, boolean isSeasonal,
                                        String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long id) throws Exception;
    public Food updateAvailabilityStatus(Long foodId) throws Exception;

}
