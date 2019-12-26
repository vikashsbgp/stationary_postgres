package com.stationary.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.dto.OrderResponseDTO;
import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.Product;
import com.stationary.demo.service.OrderService;
import com.stationary.demo.service.UserServices;

@RestController
public class OrderController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	public OrderResponseDTO placeOrder(@RequestBody List<Product> product) {
		return orderService.saveOrder(product);
	}
	
	@GetMapping("/order")
	public List<OrderResponseDTO> getOrder() {
		return orderService.getOrder();
	}
	
	@PutMapping("/order")
	public OrderResponseDTO updateOrder(@RequestBody Order order) {
		return orderService.updateOrder(order);
	}
	
	@DeleteMapping("/{id}/order")
	public void deleteOrder(@PathVariable("id") String order_id) {
		orderService.deleteOrder(order_id);
	}

}
