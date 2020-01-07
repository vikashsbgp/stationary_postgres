package com.stationary.demo.service;

import java.util.List;

import com.stationary.demo.dto.CartResponseDTO;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;

public interface CartService {
	
	public List<Product> getCart(User user);
	
	CartResponseDTO addToCart(Boolean edit, Integer quantity, Product products);
	
	public void deleteCart(long product_id);

}
