package com.stationary.demo.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stationary.demo.entities.ProductStatus;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
	
	@Modifying
	@Query(value = "Insert into product_status (status, user_id, product_id, order_id) VALUES (:status, :user_id, :prod_id, :order_id)", nativeQuery = true)
	@Transactional 
	void saveRow(@Param("status") String status, @Param("user_id") long user_id,
			@Param("prod_id") long prod_id, @Param("order_id") String order_id);
	
	
	@Query(value = "SELECT * FROM product_status ps where ps.order_id=:order_id", nativeQuery = true)
	public List<ProductStatus> findByOrderId(@Param("order_id") String order_id);

}
