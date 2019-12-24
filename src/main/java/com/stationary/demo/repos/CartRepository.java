package com.stationary.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stationary.demo.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
