package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.CartResponseDTO;
import com.stationary.demo.dto.ProductResponseDTO;
import com.stationary.demo.entities.Cart;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository cartRepository;

	@Autowired
	UserServices userServices;
	
	@Override
	public List<Product> getCart(User user) {
		
		long cart_id = userServices.getCartOfUser(user.getId());
		Cart cart = cartRepository.findById(cart_id).get();
		return cart.getProducts();
	}

	@Override
	public CartResponseDTO addToCart(Boolean edit, Integer quantity, Product product) {
		
		Cart cart;
		CartResponseDTO cartdto = new CartResponseDTO();
		List<ProductResponseDTO> productsdto = new ArrayList<ProductResponseDTO>();
		
		User user = userServices.getUser();
		ProductResponseDTO productdto = new ProductResponseDTO();
		productdto.setId(product.getId());
		productdto.setName(product.getName());
		productdto.setBrand(product.getBrand());
		productdto.setDescription(product.getDescription());
		productdto.setPrice(product.getPrice());
		productdto.setQuantity(product.getQuantity());
		productsdto.add(productdto);
		
		if (user.getCart() != null) {
			cart = cartRepository.findById(user.getCart().getId()).get();
			if(edit != null && edit==true) {
				for(Product prod:cart.getProducts()) {
					if (prod.getId() == product.getId()) {
						if (quantity == 0) {
							cart.getProducts().remove(prod);
							prod.setQuantity(quantity);
							break;
						}
						else if (quantity > 5)
							return null;
						prod.setQuantity(quantity);
						productdto.setQuantity(quantity);
					}
				}
			}
			else {
				cart.getProducts().add(product);
			}
		}
		else {
			cart = new Cart();
			cart.setProducts(Arrays.asList(product));
		}
		cart = cartRepository.save(cart);
		userServices.addCartToUser(cart, user);
		cartdto.setProducts(productsdto);
		return cartdto;
	}

	@Override
	public void deleteCart(long product_id) {
		
		User user = userServices.getUser();
		Cart cart = user.getCart();
		
		if (cart != null) {
			
			for (Product prod:cart.getProducts()) {
				if (prod.getId() == product_id) {
					cart.getProducts().remove(prod);
					break;
				}
			}
			
		}
		
		cartRepository.save(cart);
		
	}

}
