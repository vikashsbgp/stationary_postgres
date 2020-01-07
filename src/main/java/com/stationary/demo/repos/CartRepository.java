package com.stationary.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stationary.demo.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = "delete from cart_product where product_id=:product_id", nativeQuery = true)
	void deleteByProductId(@Param("product_id") long product_id);
	
}
