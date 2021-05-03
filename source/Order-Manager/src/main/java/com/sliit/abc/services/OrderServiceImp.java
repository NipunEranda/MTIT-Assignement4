package com.sliit.abc.services;

import java.util.ArrayList;

import org.json.JSONObject;

import com.sliit.abc.model.Item;

public class OrderServiceImp implements OrderService{

	@Override
	public JSONObject order(long user, String orderedDate, double totalPrice, ArrayList<Item> items) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject updateOrder(long user, String orderedDate, double totalPrice, ArrayList<Item> items,
			String reason, long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject removeOrder(int order, String reason) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getOrderById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
