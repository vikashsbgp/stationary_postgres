package com.stationary.demo.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrderResponseDTO {
	private String id;
	private List<ProductResponseDTO> products;
	private long user_id;
	private AddressResponseDTO address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ProductResponseDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponseDTO> products) {
		this.products = products;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public AddressResponseDTO getAddress() {
		return address;
	}

	public void setAddress(AddressResponseDTO address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderResponseDTO [id=" + id + ", products=" + products + ", user_id=" + user_id + ", address=" + address
				+ "]";
	}

}
