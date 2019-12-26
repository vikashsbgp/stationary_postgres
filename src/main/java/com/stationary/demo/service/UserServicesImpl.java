package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.AddressResponseDTO;
import com.stationary.demo.dto.UserResponseDTO;
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
	public UserResponseDTO registerUser(User user) {
		
		UserResponseDTO userdto = new UserResponseDTO();
		userdto.setFirstName(user.getFirstName());
		userdto.setLastName(user.getLastName());
		userdto.setEmail(user.getEmail());
		userdto.setMobile(user.getMobile());
		user = userRepository.save(user);
		List<Address> addresses = user.getAddresses();
		for (Address address : addresses) {
			address.setUser(user);
		}
		addressRepository.saveAll(user.getAddresses());
		List<AddressResponseDTO> addressesdto = new ArrayList<AddressResponseDTO>();
		
		for (Address address : user.getAddresses()) {
			AddressResponseDTO addressdto = new AddressResponseDTO();
			addressdto.setCity(address.getCity());
			addressdto.setCountry(address.getCountry());
			addressdto.setState(address.getState());
			addressdto.setStreet(address.getStreet());
			addressdto.setZipcode(address.getZipcode());
			addressesdto.add(addressdto);
			
		}
		userdto.setAddress(addressesdto);
		return userdto;
		
	}

	@Override
	public User getUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) auth.getPrincipal();
		User user = userRepository.findByEmail(username);
		return user;
	}


}
