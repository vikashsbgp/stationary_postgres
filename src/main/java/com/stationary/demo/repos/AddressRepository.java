package com.stationary.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stationary.demo.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
