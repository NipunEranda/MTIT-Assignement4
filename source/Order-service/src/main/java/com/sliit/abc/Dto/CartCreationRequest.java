package com.sliit.abc.Dto;

public class CartCreationRequest {
	
	private String productName;
    private int quantity;
    
    
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
	
	@Override
	public String toString() {
		return "UserRequest [productName=" + productName + ", quantity=" + quantity + "]";
	}
    

}
