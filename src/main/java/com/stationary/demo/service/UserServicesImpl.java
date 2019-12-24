package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stationary.demo.entities.Address;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.AddressRepository;
import com.stationary.demo.repos.UserRepository;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		
		List<Address> addressList = new ArrayList<>();
		List<Address> addressList1 = user.getAddresses();
		user.setAddresses(null);
		user = userRepository.save(user);
		for (Address address : addressList1) {
			address.setUser(user);
			addressList.add(address);
		}
		addressRepository.saveAll(addressList);
		return user;
	}

	@Override
	public User getUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		User user = userRepository.findByEmail(username);
		return user;
	}


}
