package com.stationary.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.dto.AddressResponseDTO;
import com.stationary.demo.entities.Address;
import com.stationary.demo.repos.AddressRepository;
import com.stationary.demo.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressRepository addressRepository;

	@PostMapping("/address")
	public AddressResponseDTO createAddress(@RequestBody Address addressreq) {
		return addressService.create(addressreq);
	}

	@PutMapping("/address")
	public AddressResponseDTO updateAddress(@RequestBody Address addressreq) {
		return addressService.update(addressreq);
	}

	@GetMapping("/address")
	public List<AddressResponseDTO> getAddress() {
		return addressService.read();
	}
	
	@DeleteMapping("/{address_id}/address")
	public void deleteAddress(@PathVariable("address_id") long address_id) {
		addressRepository.deleteById(address_id);
	}

}
