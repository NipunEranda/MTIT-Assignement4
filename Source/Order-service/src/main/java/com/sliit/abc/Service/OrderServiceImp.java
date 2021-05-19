package com.sliit.abc.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sliit.abc.Dto.CartCreationRequest;
import com.sliit.abc.Dto.CartCreationResponse;
import com.sliit.abc.Dto.ItemResponse;
import com.sliit.abc.Dto.OrderRequest;
import com.sliit.abc.Dto.OrderResponse;
import com.sliit.abc.Dto.PaymentCreationRequest;
import com.sliit.abc.Dto.PaymentCreationResponse;
import com.sliit.abc.Dto.UserResponse;

import java.util.UUID;

@Service
public class OrderServiceImp {

	@Autowired
	private RestTemplate restTemplate;

	public OrderResponse makeOrder(OrderRequest orderRequest) {
		try {
			var paymentCreationRequest = new PaymentCreationRequest();
			var cartCreationRequest = new CartCreationRequest();

			ResponseEntity<UserResponse> customerRetrieve = restTemplate
					.getForEntity("http://localhost:1111/api/" + orderRequest.getCustomerId(), UserResponse.class);

			ResponseEntity<ItemResponse> itemRetrieve = restTemplate.getForEntity(
					"http://localhost:2222/api/" + String.valueOf(orderRequest.getProduct()), ItemResponse.class);

			paymentCreationRequest.setCardType(orderRequest.getCardType());
			paymentCreationRequest.setCardNum(orderRequest.getCardNum());
			paymentCreationRequest.setCardHolder(orderRequest.getCardHolder());

			cartCreationRequest.setProduct(orderRequest.getProduct());
			cartCreationRequest.setQuantity(orderRequest.getQuantity());
			cartCreationRequest.setCustomer(Long.parseLong(orderRequest.getCustomerId()));
			cartCreationRequest.setPrice(Double.parseDouble(itemRetrieve.getBody().getPrice()));

			ResponseEntity<PaymentCreationResponse> paymentCreationResponse = restTemplate.postForEntity(
					"http://localhost:8080/payment", paymentCreationRequest, PaymentCreationResponse.class);
			ResponseEntity<CartCreationResponse> cartCreationResponse = restTemplate
					.postForEntity("http://localhost:8585/cart", cartCreationRequest, CartCreationResponse.class);

			var orderResponse = new OrderResponse();
			String orderId = UUID.randomUUID().toString();
			orderResponse.setOrderId(orderId);
			orderResponse.setCustomer(
					new UserResponse(orderRequest.getCustomerId(), customerRetrieve.getBody().getFirstName(),
							customerRetrieve.getBody().getLastName(), customerRetrieve.getBody().getInitials(),
							customerRetrieve.getBody().getPhoneNo(), customerRetrieve.getBody().getAddress()));

			ItemResponse i = new ItemResponse();
			i.setId(orderRequest.getProduct());
			i.setName(itemRetrieve.getBody().getName());
			i.setDescription(itemRetrieve.getBody().getDescription());
			i.setPrice(itemRetrieve.getBody().getPrice());
			i.setItemType(itemRetrieve.getBody().getItemType());
			orderResponse.setProduct(i);

			orderResponse.setPaymentId(paymentCreationResponse.getBody().getPaymentID());
			orderResponse.setCartId(cartCreationResponse.getBody().getCartId());
			orderResponse.setMessage("Order placed");

			DBManager.createOrder(orderId, Long.parseLong(orderRequest.getCustomerId()), orderRequest.getProduct(),
					paymentCreationResponse.getBody().getPaymentID(), cartCreationResponse.getBody().getCartId());
			return orderResponse;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public OrderResponse getOrder(String id) {

		JSONObject j = DBManager.getOrderById(id);
		System.out.println("Product : " + String.valueOf(j.getLong("product")));

		ResponseEntity<UserResponse> customerRetrieve = restTemplate
				.getForEntity("http://localhost:1111/api/" + j.getLong("customer"), UserResponse.class);

		ResponseEntity<ItemResponse> itemRetrieve = restTemplate
				.getForEntity("http://localhost:2222/api/" + String.valueOf(j.getLong("product")), ItemResponse.class);

		OrderResponse or = new OrderResponse();
		or.setCustomer(new UserResponse(String.valueOf(j.getLong("customer")), customerRetrieve.getBody().getFirstName(),
				customerRetrieve.getBody().getLastName(), customerRetrieve.getBody().getInitials(),
				customerRetrieve.getBody().getPhoneNo(), customerRetrieve.getBody().getAddress()));
		ItemResponse i = new ItemResponse();
		i.setId(j.getLong("product"));
		i.setName(itemRetrieve.getBody().getName());
		i.setDescription(itemRetrieve.getBody().getDescription());
		i.setPrice(itemRetrieve.getBody().getPrice());
		i.setItemType(itemRetrieve.getBody().getItemType());
		or.setProduct(i);
		or.setCartId(j.getString("cartId"));
		or.setOrderId(id);
		or.setPaymentId(j.getString("paymentId"));
		or.setMessage("order");
		return or;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
