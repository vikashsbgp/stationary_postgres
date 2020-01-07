package com.stationary.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stationary.demo.entities.Order;
import com.stationary.demo.entities.User;

public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findByUser(User user);
}
