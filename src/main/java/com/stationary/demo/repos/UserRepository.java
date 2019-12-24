package com.stationary.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stationary.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String username);
	User findByMobile(String mobile);
	
	@Query(value = "select u.cart_id from users u where u.id=:user_id", nativeQuery = true)
	Long getCartId(@Param("user_id") long user_id);

}
