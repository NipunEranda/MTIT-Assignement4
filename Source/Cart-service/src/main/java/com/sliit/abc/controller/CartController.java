package com.sliit.abc.controller;

import com.sliit.abc.dto.*;
import com.sliit.abc.service.DBManager;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

	@PostMapping(consumes = "application/json", produces = "application/json")
	public @ResponseBody CartResponse createCart(@RequestBody CartRequest cartRequest) {

		System.out.println("Cart details : " + cartRequest);

		var cartResponse = new CartResponse();
		String cartId = UUID.randomUUID().toString();
		cartResponse.setCartId(cartId);
		cartResponse.setMessage("Successfully created Shopping Cart");
		DBManager.createCart(cartId, cartRequest.getCustomer(), cartRequest.getProduct(), cartRequest.getPrice(), cartRequest.getQuantity());
		return cartResponse;
	}

}
