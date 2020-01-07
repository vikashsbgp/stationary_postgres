package com.stationary.demo.service;

import com.stationary.demo.dto.UserResponseDTO;
import com.stationary.demo.entities.Cart;
import com.stationary.demo.entities.User;

public interface UserServices {

	public UserResponseDTO registerUser(User user);
	
	public User getUser();

	void addCartToUser(Cart cart, User user);

	long getCartOfUser(long user_id);
	
}
