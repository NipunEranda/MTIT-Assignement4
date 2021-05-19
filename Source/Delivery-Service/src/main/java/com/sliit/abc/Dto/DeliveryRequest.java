package com.sliit.abc.Dto;

import java.util.UUID;

public class DeliveryRequest {

	private long customerId;
	private String orderId;
	private String deliveryDate;
	private String deliveryDescription;

	public long getCustomerId() {
		return customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	@Override
	public String toString() {
		return "{'customerId':'" + customerId + "','orderId':'" + orderId + "', 'deliveryDescription':'"
				+ deliveryDescription + "'}";
	}
}
