package com.stationary.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;
import com.stationary.demo.service.CartService;
import com.stationary.demo.service.UserServices;
import com.stationary.demo.utilities.ResponseLibrary;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/cart")
	public ResponseEntity<ResponseLibrary> addCart(@RequestParam(required = false) Boolean edit,@RequestParam(required = false) Integer quantity, @RequestBody Product products) {
		ResponseLibrary res = new ResponseLibrary();
		res.setData(cartService.addToCart(edit,quantity,products));
		res.setError(false);
		res.setMessage("Added to Cart Successfully");
		res.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/cart")
	public ResponseEntity<ResponseLibrary> getCart() {
		
		User user = userServices.getUser();
		ResponseLibrary res = new ResponseLibrary();
		res.setData(cartService.getCart(user));
		res.setError(false);
		res.setMessage("Fetched Cart Successfully");
		res.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{product_id}/cart")
	public ResponseEntity<ResponseLibrary> deleteCart(@PathVariable("product_id") long product_id) {
		
		cartService.deleteCart(product_id);
		ResponseLibrary res = new ResponseLibrary();
		res.setData(null);
		res.setError(false);
		res.setMessage("Deleted Successfully");
		res.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(res, HttpStatus.ACCEPTED);
		
	}

}
