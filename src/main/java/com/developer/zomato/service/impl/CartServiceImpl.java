package com.developer.zomato.service.impl;

import com.developer.zomato.entity.Cart;
import com.developer.zomato.entity.CartItems;
import com.developer.zomato.request.AddCartItemRequest;
import com.developer.zomato.service.CartService;

public class CartServiceImpl implements CartService {
    @Override
    public CartItems addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        return null;
    }

    @Override
    public CartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        return null;
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        return null;
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        return null;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        return null;
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        return null;
    }
}
