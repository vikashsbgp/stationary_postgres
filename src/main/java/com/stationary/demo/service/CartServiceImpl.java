package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.CartResponseDTO;
import com.stationary.demo.dto.ProductResponseDTO;
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

	@Autowired
	UserServices userServices;
	
	@Override
	public List<Product> getCart(User user) {
		
		long cart_id = userRepository.getCartId(user.getId());
		Cart cart = cartRepository.findById(cart_id).get();
		return cart.getProducts();
	}

	@Override
	public CartResponseDTO addToCart(List<Product> products) {
		
		Cart cart;
		CartResponseDTO cartdto = new CartResponseDTO();
		List<ProductResponseDTO> productsdto = new ArrayList<ProductResponseDTO>();
		
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
		
		for (Product prod : products) {
			ProductResponseDTO productdto = new ProductResponseDTO();
			productdto.setId(prod.getId());
			productdto.setName(prod.getName());
			productdto.setBrand(prod.getBrand());
			productdto.setDescription(prod.getDescription());
			productdto.setPrice(prod.getPrice());
			productdto.setQuantity(prod.getQuantity());
			productsdto.add(productdto);
		}
		cartdto.setProducts(productsdto);
		return cartdto;
	}

}
