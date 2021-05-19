package com.sliit.abc.Dto;

public class OrderRequest {
	
	private String orderType;
	private String orderDetails;
	private String productName;
	private int quantity;
	private int amount;
	private String cardType;
	private String cardNum;
	private String cardHolder;
	
	
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
		return "OrderRequest [orderType=" + orderType + ", orderDetails=" + orderDetails + ", productName="
				+ productName + ", quantity=" + quantity + ", amount=" + amount + ", cardType=" + cardType
				+ ", cardNum=" + cardNum + ", cardHolder=" + cardHolder + "]";
	}
	
	

}
