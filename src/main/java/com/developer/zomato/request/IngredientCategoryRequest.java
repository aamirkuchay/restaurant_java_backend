package com.developer.zomato.request;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class IngredientCategoryRequest {

    private String name;
    private Long restaurantId;
}
