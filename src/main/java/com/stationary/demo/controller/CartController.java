package com.stationary.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.entities.Cart;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.CartRepository;
import com.stationary.demo.repos.ProductRepository;
import com.stationary.demo.repos.UserRepository;
import com.stationary.demo.service.CartService;
import com.stationary.demo.service.UserServices;

@RestController
public class CartController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/cart")
	public Cart addCart(@RequestBody List<Product> products) {
		
		Cart cart;
		User user = userServices.getUser();
		
		if (user.getCart() != null) {
			cart = cartRepository.findById(user.getCart().getId()).get();
			cart.getProducts().addAll(products);
		}
		else {
			cart = new Cart();
			cart.setProducts(products);
		}
		
		cart = cartRepository.save(cart);
		user.setCart(cart);
		userRepository.save(user);
		return cart;
		
	}
	
	@GetMapping("/cart")
	public List<Product> getCart() {
		
		User user = userServices.getUser();
		return cartService.getCart(user);
		
	}

}
