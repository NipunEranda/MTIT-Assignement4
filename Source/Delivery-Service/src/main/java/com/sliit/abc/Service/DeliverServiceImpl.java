package com.sliit.abc.Service;

import com.sliit.abc.Dto.DeliveryRequest;
import com.sliit.abc.Dto.DeliveryResponse;
import com.sliit.abc.Dto.ItemResponse;
import com.sliit.abc.Dto.ItemType;
import com.sliit.abc.Dto.OrderResponse;
import com.sliit.abc.Dto.UserReponse;
import com.sliit.abc.Dto.UserResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class DeliverServiceImpl {
	@Autowired
	private RestTemplate restTemplate;

	public DeliveryResponse createDeliver(DeliveryRequest deliveryRequest) {

		ResponseEntity<UserResponse> userResponse = restTemplate
				.getForEntity("http://localhost:1111/api/" + deliveryRequest.getCustomerId(), UserResponse.class);
		ResponseEntity<OrderResponse> orderResponse = restTemplate.getForEntity(
				"http://localhost:8282/order/" + deliveryRequest.getOrderId().toString(), OrderResponse.class);

		String deliveryId = UUID.randomUUID().toString();

		var deliveryResponse = new DeliveryResponse();
		deliveryResponse.setDeliveryId(deliveryId);
		deliveryResponse.setDeliveryDate("19/05/2021");
		deliveryResponse.setDeliveryDescription("Important item please deliver this item quickly.");
		OrderResponse or = new OrderResponse();
		or.setOrderId(orderResponse.getBody().getOrderId());
		or.setCartId(orderResponse.getBody().getCartId());
		or.setPaymentId(orderResponse.getBody().getPaymentId());
		or.setProduct(orderResponse.getBody().getProduct());
		or.setMessage("Deliver is done");
		deliveryResponse.setOrder(or);

		DBManager.addDelivery(deliveryId, deliveryRequest.getOrderId(), deliveryRequest.getCustomerId(),
				deliveryRequest.getDeliveryDate(), deliveryRequest.getDeliveryDescription());

		return deliveryResponse;
	}

	public DeliveryResponse getDeliveryDetails(String id) {

		JSONObject j = DBManager.getDelivery(id);

		System.out.println(j.toString());

		ResponseEntity<UserResponse> userResponse = restTemplate
				.getForEntity("http://localhost:1111/api/" + j.getLong("customerId"), UserResponse.class);
		ResponseEntity<OrderResponse> orderResponse = restTemplate
				.getForEntity("http://localhost:8282/order/" + j.getString("orderId"), OrderResponse.class);

		DeliveryResponse dr = new DeliveryResponse();

		OrderResponse or = new OrderResponse();

		or.setOrderId(orderResponse.getBody().getOrderId());
		or.setPaymentId(orderResponse.getBody().getPaymentId());
		or.setProduct(orderResponse.getBody().getProduct());
		or.setCartId(orderResponse.getBody().getCartId());
		or.setMessage(orderResponse.getBody().getMessage());
		or.setCustomer(new UserResponse(String.valueOf(j.getLong("customerId")), userResponse.getBody().getFirstName(),
				userResponse.getBody().getLastName(), userResponse.getBody().getInitials(),
				userResponse.getBody().getPhoneNo(), userResponse.getBody().getAddress()));
		dr.setOrder(or);
		dr.setDeliveryDate(j.getString("deliveryDate"));
		dr.setDeliveryDescription(j.getString("deliveryDescription"));
		dr.setDeliveryId(id);

		return dr;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
