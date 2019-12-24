package com.stationary.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.entities.Cart;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.CartRepository;
import com.stationary.demo.repos.UserRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;

	@Override
	public List<Product> getCart(User user) {
		
		long cart_id = userRepository.getCartId(user.getId());
		Cart cart = cartRepository.findById(cart_id).get();
		return cart.getProducts();
	}

}
