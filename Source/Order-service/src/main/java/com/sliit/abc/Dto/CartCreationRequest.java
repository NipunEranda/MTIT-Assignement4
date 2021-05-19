package com.sliit.abc.Dto;

public class CartCreationRequest {

	private long customer;
	private double price;
	private long product;
	private int quantity;

	public long getProduct() {
		return product;
	}

	public void setProduct(long product) {
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

	public double getPrice() {
		return price;
	}

	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "UserRequest [productName=" + product + ", quantity=" + quantity + "]";
	}

}
