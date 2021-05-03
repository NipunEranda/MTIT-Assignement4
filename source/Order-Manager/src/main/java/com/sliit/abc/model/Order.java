package com.sliit.abc.model;

import java.util.ArrayList;

public class Order {
	
	private long id;
	private long user;
	private String orderedDate;
	private double totalPrice;
	private ArrayList<Item> items;
	private int isProcessed;
	
	public Order(long id, long user, String orderedDate, double totalPrice, ArrayList<Item> items, int isProcessed) {
		super();
		this.id = id;
		this.user = user;
		this.orderedDate = orderedDate;
		this.totalPrice = totalPrice;
		this.items = items;
		this.isProcessed = isProcessed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public int getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(int isProcessed) {
		this.isProcessed = isProcessed;
	}

}
