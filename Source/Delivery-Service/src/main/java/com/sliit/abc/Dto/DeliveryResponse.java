package com.sliit.abc.Dto;

public class DeliveryResponse {

	private String deliveryId;
	private OrderResponse order;
	private String deliveryDate;
	private String deliveryDescription;

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public OrderResponse getOrder() {
		return order;
	}

	public void setOrder(OrderResponse order) {
		this.order = order;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryDescription() {
		return deliveryDescription;
	}

	public void setDeliveryDescription(String deliveryDescription) {
		this.deliveryDescription = deliveryDescription;
	}
}
