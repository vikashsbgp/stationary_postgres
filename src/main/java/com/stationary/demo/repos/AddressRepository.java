package com.stationary.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stationary.demo.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	@Query(value = "select * from address where default_address=true", nativeQuery = true)
	Address findByDefaultAddress();
	
}
