package com.sliit.abc.dto;

public class CartRequest {

	private long customer;
	private long product;
	private double price;
	private int quantity;

	public long getProductName() {
		return product;
	}

	public void setProductName(long product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getCustomer() {
		return customer;
	}

	public long getProduct() {
		return product;
	}

	public double getPrice() {
		return price;
	}

	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public void setProduct(long product) {
		this.product = product;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartRequest [productName=" + product + ", quantity=" + quantity + "]";
	}

}
