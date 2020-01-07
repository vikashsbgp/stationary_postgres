package com.stationary.demo.dto;

import java.util.List;

import com.stationary.demo.entities.Address;
import com.stationary.demo.entities.Product;

public class OrderRequest {
	
	private String id;
	private List<Product> products;
	private Address address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "OrderRequest [id=" + id + ", products=" + products + ", address=" + address + "]";
	}
	

}
