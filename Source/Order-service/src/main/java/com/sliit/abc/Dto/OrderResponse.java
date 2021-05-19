package com.sliit.abc.Dto;

public class OrderResponse {

	private UserResponse Customer;
	private String orderId;
	private ItemResponse product;
	private String paymentId;
	private String cartId;
	private String message;
	
	public UserResponse getCustomer() {
		return Customer;
	}

	public void setCustomer(UserResponse customer) {
		Customer = customer;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ItemResponse getProduct() {
		return product;
	}

	public void setProduct(ItemResponse product) {
		this.product = product;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
