package com.stationary.demo.service;

import java.util.List;

import com.stationary.demo.dto.ProductResponseDTO;
import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.ProductStatus;
import com.stationary.demo.entities.User;

public interface ProductStatusService {

	void saveProductStatus(Order order,long user_id, String status);
	
	List<ProductResponseDTO> saveProductToProductResponseDto(User user, Order order, String status);

	ProductStatus updateProductStatus(String status,long user_id, String order_id, long product_id);
}
