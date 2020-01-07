package com.stationary.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationary.demo.dto.ProductResponseDTO;
import com.stationary.demo.entities.Product;
import com.stationary.demo.repos.ProductRepository;
import com.stationary.demo.utilities.CsvUtils;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ProductResponseDTO> getProducts() {
		
		productRepository.findAllProducts();
		return null;
	}

	@Override
	public void uploadProducts(InputStream body) throws IOException {
		
		productRepository.saveAll(CsvUtils.read(Product.class, body));
		
	}

}
