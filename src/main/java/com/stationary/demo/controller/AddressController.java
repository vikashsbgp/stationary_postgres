package com.stationary.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.entities.Address;
import com.stationary.demo.repos.AddressRepository;
import com.stationary.demo.service.AddressService;
import com.stationary.demo.utilities.ResponseLibrary;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressRepository addressRepository;

	@PostMapping("/address")
	public ResponseEntity<ResponseLibrary> createAddress(@RequestBody Address addressreq) {
		ResponseLibrary res = new ResponseLibrary();
		res.setData(addressService.create(addressreq));
		res.setError(false);
		res.setMessage("Created Successfully");
		res.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}

	@PutMapping("/address")
	public ResponseEntity<ResponseLibrary> updateAddress(@RequestBody Address addressreq) {
		ResponseLibrary res = new ResponseLibrary();
		res.setData(addressService.update(addressreq));
		res.setError(false);
		res.setMessage("Updated Successfully");
		res.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}

	@GetMapping("/address")
	public ResponseEntity<ResponseLibrary> getAddress() {
		ResponseLibrary res = new ResponseLibrary();
		res.setData(addressService.read());
		res.setError(false);
		res.setMessage("Fetched Successfully");
		res.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{address_id}/address")
	public ResponseEntity<ResponseLibrary> deleteAddress(@PathVariable("address_id") long address_id) {
		addressRepository.deleteById(address_id);
		ResponseLibrary res = new ResponseLibrary();
		res.setData(null);
		res.setError(false);
		res.setMessage("Deleted Successfully");
		res.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}

}
