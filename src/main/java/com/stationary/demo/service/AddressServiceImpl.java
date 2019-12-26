package com.stationary.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.AddressResponseDTO;
import com.stationary.demo.entities.Address;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public AddressResponseDTO create(Address address) {
		
		User user = userServices.getUser();
		AddressResponseDTO addressdto = new AddressResponseDTO();
		addressdto.setCity(address.getCity());
		addressdto.setCountry(address.getCountry());
		addressdto.setState(address.getState());
		addressdto.setStreet(address.getStreet());
		addressdto.setZipcode(address.getZipcode());
		address.setUser(user);
		Address addrs = addressRepository.save(address);
		addressdto.setId(addrs.getId());
		addressdto.setDefaultAddress(address.isDefaultAddress());
		return addressdto;
	}

	@Override
	public List<AddressResponseDTO> read() {
			
		User user = userServices.getUser();
		List<Address> addresses = user.getAddresses();
		List<AddressResponseDTO> addressesdto = new ArrayList<AddressResponseDTO>();
		
		for (Address address : addresses) {
			AddressResponseDTO addressdto = new AddressResponseDTO();
			addressdto.setId(address.getId());
			addressdto.setCity(address.getCity());
			addressdto.setCountry(address.getCountry());
			addressdto.setState(address.getState());
			addressdto.setStreet(address.getStreet());
			addressdto.setZipcode(address.getZipcode());
			addressdto.setDefaultAddress(address.isDefaultAddress());
			addressesdto.add(addressdto);
		}
		
		return addressesdto;
	}

	@Override
	public AddressResponseDTO update(Address address) {
		
		AddressResponseDTO addressdto = new AddressResponseDTO();
		addressdto.setId(address.getId());
		addressdto.setCity(address.getCity());
		addressdto.setCountry(address.getCountry());
		addressdto.setState(address.getState());
		addressdto.setStreet(address.getStreet());
		addressdto.setZipcode(address.getZipcode());
		addressdto.setDefaultAddress(address.isDefaultAddress());
		address.setUser(userServices.getUser());
		addressRepository.save(address);
		return addressdto;
	}


}
