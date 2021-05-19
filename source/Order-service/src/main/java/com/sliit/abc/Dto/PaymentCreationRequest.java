package com.sliit.abc.Dto;

public class PaymentCreationRequest {
	
	private String cardType;
	private String cardNum;
	private String cardHolder;
	
	
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
		return "PaymentCreationRequest [cardType=" + cardType + ", cardNum=" + cardNum + ", cardHolder=" + cardHolder
				+ "]";
	}
	
	
	
	

}
