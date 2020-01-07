package com.stationary.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stationary.demo.entities.Product;
import com.stationary.demo.repos.ProductRepository;
import com.stationary.demo.service.ProductService;
import com.stationary.demo.utilities.ResponseLibrary;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<ResponseLibrary> createProduct(@RequestBody List<Product> products) {
		ResponseLibrary resp = new ResponseLibrary();
		resp.setData(productRepository.saveAll(products));
		resp.setError(false);
		resp.setMessage("Created successfully");
		resp.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(resp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/products")
	public ResponseEntity<ResponseLibrary> getProduct() {
		ResponseLibrary resp = new ResponseLibrary();
		resp.setData(productRepository.findAllProducts());
		resp.setError(false);
		resp.setMessage("All products");
		resp.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(resp, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/products/upload", consumes = "multipart/form-data")
	public ResponseEntity<ResponseLibrary> uploadProducts(@RequestParam("file") MultipartFile file) throws IOException {
		productService.uploadProducts(file.getInputStream());
		ResponseLibrary resp = new ResponseLibrary();
		resp.setData(null);
		resp.setError(false);
		resp.setMessage("Uploaded Successfully");
		resp.setStatus(200);
		return new ResponseEntity<ResponseLibrary>(resp, HttpStatus.ACCEPTED);
	}

}
