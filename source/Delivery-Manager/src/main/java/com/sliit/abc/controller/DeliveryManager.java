package com.sliit.abc.controller;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class DeliveryManager {
	
	@RequestMapping("/")
    String home() {
		try {
	        return new JSONObject().put("message", "Delivery Manager").toString();
		}catch (Exception e) {
			return new JSONObject().put("status", e.getMessage()).toString();
		}
    }
 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DeliveryManager.class, args);
    }

}
