package com.stationary.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.entities.Product;
import com.stationary.demo.repos.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/products")
	public List<Product> createProduct(@RequestBody List<Product> products) {
		return productRepository.saveAll(products);
	}

	@GetMapping("/products")
	public List<Product> getProduct() {
		return productRepository.findAll();
	}

}
