package com.sliit.abc.Service;

import com.sliit.abc.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderServiceImp {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public OrderResponse makeOrder(OrderRequest orderRequest) {
		var paymentCreationRequest = new PaymentCreationRequest();
		var cartCreationRequest = new CartCreationRequest();
		
		paymentCreationRequest.setCardType(orderRequest.getCardType());
		paymentCreationRequest.setCardNum(orderRequest.getCardNum());
		paymentCreationRequest.setCardHolder(orderRequest.getCardHolder());
		
		cartCreationRequest.setProductName(orderRequest.getProductName());
		cartCreationRequest.setQuantity(orderRequest.getQuantity());
	
		ResponseEntity<PaymentCreationResponse> paymentCreationResponse = restTemplate.postForEntity("http://localhost:8080/payment", paymentCreationRequest, PaymentCreationResponse.class);
		ResponseEntity<CartCreationResponse> cartCreationResponse = restTemplate.postForEntity("http://localhost:8585/cart", cartCreationRequest, CartCreationResponse.class);
		
		var orderResponse = new OrderResponse();
		orderResponse.setOrderId(UUID.randomUUID().toString());
		orderResponse.setPaymentId(paymentCreationResponse.getBody().getPaymentID());
		orderResponse.setCartId(cartCreationResponse.getBody().getCartId());
		orderResponse.setMessage("Order placed");
		
		return orderResponse;
		
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
