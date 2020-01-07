package com.stationary.demo.dto;

import org.springframework.stereotype.Component;

import com.stationary.demo.entities.ProductStatus;

@Component
public class ProductResponseDTO {

	private long id;
	private String name;
	private String description;
	private String brand;
	private double price;
	private int quantity;
	private ProductStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductResponseDTO [id=" + id + ", name=" + name + ", description=" + description + ", brand=" + brand
				+ ", price=" + price + ", quantity=" + quantity + ", status=" + status + "]";
	}


}
