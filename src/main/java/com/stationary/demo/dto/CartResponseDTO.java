package com.stationary.demo.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CartResponseDTO {

	private List<ProductResponseDTO> products;

	public List<ProductResponseDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponseDTO> products) {
		this.products = products;
	}
	
}
