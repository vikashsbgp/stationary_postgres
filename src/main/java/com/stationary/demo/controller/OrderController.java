package com.stationary.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.Product;
import com.stationary.demo.entities.User;
import com.stationary.demo.repos.OrderRepository;
import com.stationary.demo.repos.ProductRepository;
import com.stationary.demo.repos.UserRepository;
import com.stationary.demo.service.UserServices;

@RestController
public class OrderController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/order")
	public Order placeOrder(@RequestBody List<Product> product) {
		
		User user = userServices.getUser();
		Order order = new Order();
		List<Order> orders = new ArrayList<>();
		order.setProduct(product);
		order.setDate(new Date());
		List<Order> userOrders = user.getOrder();
		user.setOrder(null);
		order.setUser(user);
		order = orderRepository.save(order);
		order.setUser(null);
		
		List<Product> products = order.getProduct();
		//delete order in product
		
		if (userOrders.size() != 0) {
			userOrders.add(order);
			user.setOrder(userOrders);
		}
		else {
			orders.add(order);
			user.setOrder(orders);
		}
		userRepository.save(user);
		return order;
		
	}
	
	@GetMapping("/order")
	public List<Order> getOrder() {
		
		return userServices.getUser().getOrder();
		
	}

}
