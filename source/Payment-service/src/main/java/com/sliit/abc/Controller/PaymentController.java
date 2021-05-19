package com.sliit.abc.Controller;

import com.sliit.abc.Dto.PaymentRequest;
import com.sliit.abc.Dto.PaymentResponse;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	
	@PostMapping(consumes="application/json",produces="application/json")
	public @ResponseBody PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) {
		System.out.println("Payment details: "+paymentRequest);
		
		var paymentResponse = new PaymentResponse();
		paymentResponse.setPaymentID(UUID.randomUUID().toString());
		paymentResponse.setMessage("Successful payment");
		
		return paymentResponse;
	}
	
	
}
