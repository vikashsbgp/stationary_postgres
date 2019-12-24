package com.stationary.demo.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Cart extends AbstractEntity {

	@ManyToMany
	@JoinTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> product;

	public List<Product> getProducts() {
		return product;
	}

	public void setProducts(List<Product> products) {
		this.product = products;
	}

	@Override
	public String toString() {
		return "Cart [products=" + product + "]";
	}


}
