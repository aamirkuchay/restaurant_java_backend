package com.developer.zomato.service.impl;

import com.developer.zomato.entity.Address;
import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.entity.User;
import com.developer.zomato.repository.AddressRepository;
import com.developer.zomato.repository.RestaurantRepository;
import com.developer.zomato.repository.UserRepository;
import com.developer.zomato.request.CreateRestaurantRequest;
import com.developer.zomato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
     RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        Address address = addressRepository.save(request.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContact(request.getContact());
        restaurant.setDescription(request.getDescription());
        restaurant.setName(request.getName());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setImages(request.getImages());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest request) throws Exception {
        Restaurant restaurant =findRestaurantById(restaurantId);
        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(request.getCuisineType());
        }
        if(restaurant.getDescription() != null){
            restaurant.setDescription(request.getDescription());
        }
        if(restaurant.getName() != null){
            restaurant.setName(request.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant == null){
            throw new Exception("Restuarant with given id not found");
        }
        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Restaurant with given id not found");
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userid) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userid);
        if(restaurant == null){
            throw new Exception("Restuarant  not found with given userID"+ userid);
        }
          return restaurant;
    }

    @Override
    public Restaurant addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant == null){
            throw new Exception("Restuarant with given id not found");
        }
//        RestaurantDto dto = new RestaurantDto();
//        dto.setDescription(restaurant.getDescription());
//        dto.setImages(restaurant.getImages());
//        dto.setTitle(restaurant.getDescription());
//        dto.setId(restaurantId);
        // first case -------------
        if(user.getFavorites().contains(restaurant)){
            user.getFavorites().remove(restaurant);
        }else user.getFavorites().add(restaurant);
        userRepository.save(user);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
