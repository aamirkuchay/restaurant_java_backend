package com.developer.zomato.repository;

import com.developer.zomato.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems,Long> {
}
