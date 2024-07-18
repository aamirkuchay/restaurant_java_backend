package com.developer.zomato.service;

import com.developer.zomato.entity.IngredientCategory;
import com.developer.zomato.entity.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientCategory createIngredientsCategory(String name,Long restaurantId) throws Exception;
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws  Exception;
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName,Long categoryId)throws Exception;
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) throws  Exception;
    public IngredientsItem updateStock(Long id) throws Exception;


}
