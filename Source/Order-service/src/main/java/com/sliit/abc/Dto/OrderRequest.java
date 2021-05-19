package com.sliit.abc.Dto;

public class OrderRequest {

	private String customerId;
	private String orderType;
	private String orderDetails;
	private long product;
	private int quantity;
	private int amount;
	private String cardType;
	private String cardNum;
	private String cardHolder;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	@Override
	public String toString() {
		return "OrderRequest [ customerId=" + customerId + ", orderType=" + orderType + ", orderDetails=" + orderDetails + ", product=" + product + ", quantity=" + quantity + ", amount=" + amount + ", cardType=" + cardType
				+ ", cardNum=" + cardNum + ", cardHolder=" + cardHolder + "]";
	}

}
