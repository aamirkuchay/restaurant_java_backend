package com.developer.zomato.service.impl;

import com.developer.zomato.entity.Category;
import com.developer.zomato.entity.Food;
import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.repository.FoodRepository;
import com.developer.zomato.request.CreateFoodRequest;
import com.developer.zomato.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setName(req.getName());
        food.setImages(req.getImages());
        food.setPrice(req.getPrice());
        food.setIngredientsItems(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVeg());
        Food saveFood = foodRepository.save(food);
        restaurant.getFood().add(saveFood);
        return  saveFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = foodRepository.findById(foodId).orElseThrow(()-> new Exception("food with this id is not present"));
        food.setRestaurant(null);
        foodRepository.save(food);
//        foodRepository.deleteById(food.getId());

    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVeg, boolean isNonVeg,
                                        boolean isSeasonal, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if(isVeg){
            foods = filterByVegeterian(foods,isVeg);
        }
        if(isNonVeg){
            foods = filterByNonVegeterian(foods,isNonVeg);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }
        if(foodCategory != null && !foodCategory.isEmpty()){
            foods = filterByCategory(foods,foodCategory);
        }
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(f-> {
            if(f.getFoodCategory() != null){
                return f.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(f->f.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegeterian(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(f->f.isVegetarian() == false).collect(Collectors.toList());
    }

    private List<Food> filterByVegeterian(List<Food> foods, boolean isVeg) {
        return foods.stream().filter(f->f.isVegetarian() == isVeg).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long id) throws Exception {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()){
            throw new  Exception("Food with current id not found"+id);
        }
        return food.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return  foodRepository.save(food);
    }
}
