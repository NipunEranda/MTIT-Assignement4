package com.sliit.abc.dto;

public class CartResponse {
	
	private String cartId;
    private String message;

    

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
