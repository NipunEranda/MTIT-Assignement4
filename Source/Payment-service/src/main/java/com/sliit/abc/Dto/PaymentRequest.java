package com.sliit.abc.Dto;

public class PaymentRequest {
	
	private int amount;
	private String cardType;
	private String cardNum;
	private String cardHolder;
	
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
		return "PaymentRequest [amount=" + amount + ", cardType=" + cardType + ", cardNum=" + cardNum + ", cardHolder="
				+ cardHolder + "]";
	}
	
	
	
	
	
	
	

}
