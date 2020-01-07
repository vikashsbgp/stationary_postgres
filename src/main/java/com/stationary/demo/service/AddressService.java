package com.stationary.demo.service;

import java.util.List;

import com.stationary.demo.dto.AddressResponseDTO;
import com.stationary.demo.dto.OrderRequest;
import com.stationary.demo.entities.Address;

public interface AddressService {

	AddressResponseDTO create(Address addressreq);
	List<AddressResponseDTO> read();
	AddressResponseDTO update(Address addressreq);
	Address getOrderAddress(OrderRequest orderReq);
	AddressResponseDTO saveAddressToAddressResponseDTO(Address address);
	
}
