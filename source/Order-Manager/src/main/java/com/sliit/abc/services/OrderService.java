package com.sliit.abc.services;

import java.util.ArrayList;

import org.json.JSONObject;

import com.sliit.abc.model.Item;

public interface OrderService {
	
	//POST
	public JSONObject order(long user, String orderedDate, double totalPrice, ArrayList<Item> items);
	
	//PUT
	public JSONObject updateOrder(long user, String orderedDate, double totalPrice, ArrayList<Item> items, String reason, long orderId);
	
	//DELETE
	public JSONObject removeOrder(int order, String reason);
	
	//GET
	public JSONObject getAllOrders();
	public JSONObject getOrderById(String id);

}
