package com.stationary.demo.service;

import java.util.List;

import com.stationary.demo.dto.OrderRequest;
import com.stationary.demo.dto.OrderResponseDTO;

public interface OrderService {
	
	public OrderResponseDTO saveOrder(OrderRequest orderReq);
	public List<OrderResponseDTO> getOrder();
	public String updateOrder(String status ,String order_id, Long product_id, OrderRequest orderReq);
	public void deleteOrder(String order_id);
	OrderResponseDTO exchangeOrder(OrderRequest orderReq);
	
}
