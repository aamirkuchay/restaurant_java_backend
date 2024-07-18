package com.developer.zomato.service;

import com.developer.zomato.entity.Cart;
import com.developer.zomato.entity.CartItems;
import com.developer.zomato.request.AddCartItemRequest;

public interface CartService {

    public CartItems addItemToCart(AddCartItemRequest req, String jwt) throws Exception;
    public CartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt)throws Exception;
    public Long calculateCartTotal(Cart cart) throws Exception;
    public Cart findCartById(Long id)throws Exception;
    public Cart findCartByUserId(Long userId)throws Exception;
    public Cart clearCart(Long userId) throws Exception;
}
