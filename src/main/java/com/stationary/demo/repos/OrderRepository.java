package com.stationary.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stationary.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
	
}
