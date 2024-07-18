package com.developer.zomato.request;

import com.developer.zomato.entity.Category;
import com.developer.zomato.entity.IngredientsItem;
import com.developer.zomato.entity.Restaurant;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Double price;
    private Category category;
    private List<String> images;
    private Restaurant restaurant;
    private Long restaurantId;
    private boolean veg;
    private boolean nonVeg;
    private boolean seasonal;
    private List<IngredientsItem> ingredients;


}
