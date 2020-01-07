package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.AddressResponseDTO;
import com.stationary.demo.dto.UserResponseDTO;
import com.stationary.demo.entities.Address;
import com.stationary.demo.entities.Cart;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.AddressRepository;
import com.stationary.demo.repos.UserRepository;

@Service
public class UserServicesImpl implements UserServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServicesImpl.class);
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserResponseDTO registerUser(User user) {
		LOGGER.info("Inside registerUser function of UserService");
		UserResponseDTO userdto = new UserResponseDTO();
		LOGGER.info("Setting the value of user in UserResponseDto");
		userdto.setFirstName(user.getFirstName());
		userdto.setLastName(user.getLastName());
		userdto.setEmail(user.getEmail());
		userdto.setMobile(user.getMobile());
		user = userRepository.save(user);
		LOGGER.info("User saved successfully " + user);
		List<Address> addresses = user.getAddresses();
		LOGGER.info("Get the address of the user " + addresses);
		for (Address address : addresses) {
			address.setUser(user);
		}
		LOGGER.info("Set the user id in address class");
		addressRepository.saveAll(user.getAddresses());
		LOGGER.info("Address saved successfully");
		List<AddressResponseDTO> addressesdto = new ArrayList<AddressResponseDTO>();
		LOGGER.info("Setting the address value in AddressResponseDto");
		for (Address address : user.getAddresses()) {
			AddressResponseDTO addressdto = new AddressResponseDTO();
			addressdto.setCity(address.getCity());
			addressdto.setCountry(address.getCountry());
			addressdto.setState(address.getState());
			addressdto.setStreet(address.getStreet());
			addressdto.setZipcode(address.getZipcode());
			addressesdto.add(addressdto);
			
		}
		LOGGER.info("Set the address in UserResponseDto");
		userdto.setAddress(addressesdto);
		LOGGER.info("Return the UserResponseDTO to UserController");
		return userdto;
		
	}

	@Override
	public User getUser() {
		LOGGER.info("Inside getUser method");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		LOGGER.info("Get the user by SecurityContextHolder user - " + username);
		User user = userRepository.findByEmail(username);
		LOGGER.info("Find user by email id and return the user object to UserController " + user);
		return user;
	}
	
	@Override
	public void addCartToUser(Cart cart,User user) {
		LOGGER.info("Inside addCartToUser method");
		user.setCart(cart);
		LOGGER.info("Set the cart id in user table " + cart);
		userRepository.save(user);
		LOGGER.info("Saved user successfully in db");
	}
	
	@Override
	public long getCartOfUser(long user_id) {
		LOGGER.info("Inside getCartOfUser method with parameter userid - " + user_id);
		LOGGER.info("Get CartId by passing user_id");
		return userRepository.getCartId(user_id);
	}


}
