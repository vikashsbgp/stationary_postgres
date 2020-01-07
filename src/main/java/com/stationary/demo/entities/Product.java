package com.stationary.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Product extends AbstractEntity {

	@Column(columnDefinition = "text")
	private String name;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Column(columnDefinition = "text")
	private String brand;
	
	private double price;
	private int quantity;
	
	@ManyToMany(mappedBy="product")
	private transient List<Cart> carts;
	
	@ManyToMany(mappedBy="product")
	private transient List<Order> order;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", brand=" + brand + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
