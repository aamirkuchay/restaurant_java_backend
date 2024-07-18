package com.developer.zomato.controller;

import com.developer.zomato.entity.IngredientCategory;
import com.developer.zomato.entity.IngredientsItem;
import com.developer.zomato.request.IngredientCategoryRequest;
import com.developer.zomato.request.IngredientRequest;
import com.developer.zomato.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredient")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;


    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req) throws Exception {
        IngredientCategory item = ingredientsService.createIngredientsCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req) throws Exception {
        IngredientsItem item = ingredientsService.createIngredientItem(req.getRestaurantId(),req.getName(),req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(@PathVariable Long id) throws Exception {
        List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantCategories(@PathVariable Long id) throws Exception {
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}
