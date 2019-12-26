package com.stationary.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.dto.CartResponseDTO;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;
import com.stationary.demo.service.CartService;
import com.stationary.demo.service.UserServices;

@RestController
public class CartController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/cart")
	public CartResponseDTO addCart(@RequestBody List<Product> products) {
		
		return cartService.addToCart(products);
		
	}
	
	@GetMapping("/cart")
	public List<Product> getCart() {
		
		User user = userServices.getUser();
		return cartService.getCart(user);
		
	}

}
