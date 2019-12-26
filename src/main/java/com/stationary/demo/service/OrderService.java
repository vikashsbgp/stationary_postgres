package com.stationary.demo.service;

import java.util.List;

import com.stationary.demo.dto.OrderResponseDTO;
import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.Product;

public interface OrderService {
	
	public OrderResponseDTO saveOrder(List<Product> product);
	public List<OrderResponseDTO> getOrder();
	public OrderResponseDTO updateOrder(Order order);
	public void deleteOrder(String order_id);
	
}
