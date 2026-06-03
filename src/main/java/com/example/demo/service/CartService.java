package com.example.demo.service;

import java.util.List;
import com.example.demo.dtos.CartDTO;

public interface CartService {
    List<CartDTO> findByAccountId(Integer accountId);  
    boolean addToCart(CartDTO cartDTO);  
    boolean updateCartItem(Integer id, CartDTO cartDTO);  
    boolean removeCartItem(Integer id);  
    float calculateTotal(Integer accountId);  
}