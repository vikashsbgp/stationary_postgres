package com.stationary.demo.entities;

import javax.persistence.Entity;

@Entity
public class ProductStatus extends AbstractEntity {

	private String status;
	private long user_id;
	private long product_id;
	private String order_id;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "ProductStatus [status=" + status + ", user_id=" + user_id + ", product_id=" + product_id + ", order_id="
				+ order_id + "]";
	}

	

}
