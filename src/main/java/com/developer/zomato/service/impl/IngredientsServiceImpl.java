package com.developer.zomato.service.impl;

import com.developer.zomato.entity.IngredientCategory;
import com.developer.zomato.entity.IngredientsItem;
import com.developer.zomato.entity.Restaurant;
import com.developer.zomato.repository.IngredientCategoryRepository;
import com.developer.zomato.repository.IngredientsItemRepository;
import com.developer.zomato.service.IngredientsService;
import com.developer.zomato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {

   @Autowired
   private IngredientCategoryRepository ingredientCategoryRepository;
   @Autowired
   private IngredientsItemRepository ingredientsItemRepository;
   @Autowired
   private RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw  new Exception("ingredient category with this id-> "+ id+" not present");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);
        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setIngredientCategory(category);

        IngredientsItem ingredientsItem = ingredientsItemRepository.save(item);
        category.getIngredientsItem().add(ingredientsItem);
        
        return ingredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) throws Exception {
        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem = ingredientsItemRepository.findById(id);
        if(optionalIngredientsItem.isEmpty()){
            throw new Exception("Ingredient not found");
        }
        IngredientsItem ingredientsItem = optionalIngredientsItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemRepository.save(ingredientsItem);
    }
}
