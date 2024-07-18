package com.developer.zomato.controller;

import com.developer.zomato.entity.Category;
import com.developer.zomato.entity.User;
import com.developer.zomato.service.CategoryService;
import com.developer.zomato.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Category category1 = categoryService.createCategory(category.getName(),user.getId());
        return  ResponseEntity.status(HttpStatus.CREATED).body(category1);
    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Category> getCategory = categoryService.findCategoryByRestaurantId(user.getId());
        return  ResponseEntity.status(HttpStatus.OK).body(getCategory);
    }
}
