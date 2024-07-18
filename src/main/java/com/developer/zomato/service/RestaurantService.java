package com.developer.zomato.service;

import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.entity.User;
import com.developer.zomato.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
   public Restaurant createRestaurant(CreateRestaurantRequest request, User user);
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest request) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long id) throws Exception;
    public Restaurant getRestaurantByUserId(Long userid) throws Exception;
    public Restaurant addToFavorites(Long restaurantId,User user) throws  Exception;
    public Restaurant updateRestaurantStatus(Long id) throws  Exception;




}
