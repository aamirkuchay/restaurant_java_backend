package com.developer.zomato.repository;

import com.developer.zomato.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

//    public List<Category> findBYRestaurantId(Long id);
@Query("SELECT c FROM Category c WHERE c.restaurant.id = :restaurantId")
List<Category> findByRestaurantId(@Param("restaurantId") Long restaurantId);
}
