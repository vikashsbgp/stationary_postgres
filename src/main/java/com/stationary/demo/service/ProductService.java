package com.stationary.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.stationary.demo.dto.ProductResponseDTO;

public interface ProductService {

	List<ProductResponseDTO> getProducts();
	void uploadProducts(InputStream body) throws IOException;
		
}
