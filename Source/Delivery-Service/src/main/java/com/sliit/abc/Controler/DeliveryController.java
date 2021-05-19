package com.sliit.abc.Controler;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.sliit.abc.Dto.DeliveryRequest;
import com.sliit.abc.Dto.DeliveryResponse;
import com.sliit.abc.Dto.OrderResponse;
import com.sliit.abc.Service.DeliverServiceImpl;

import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliverServiceImpl deliverService;
    
    @PostMapping(consumes = "application/json",produces = "application/json")
    public @ResponseBody DeliveryResponse createDelivery(@RequestBody DeliveryRequest request){
        System.out.println("Delivery Details : " + request);
        return deliverService.createDeliver(request);
    }
    
    @GetMapping(value = "/{id}", produces="application/json")
	public @ResponseBody
	DeliveryResponse getDeliveryDetails(@PathVariable String id) {
		return deliverService.getDeliveryDetails(id);
	}
}
