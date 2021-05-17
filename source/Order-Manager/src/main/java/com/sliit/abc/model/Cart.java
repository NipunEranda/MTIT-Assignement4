package com.sliit.abc.model;

import java.util.ArrayList;

public class Cart {
	
	private long id;
	private long user;
	private String savedDate;
	private double totalPrice;
	private ArrayList<Item> items;
	
	public Cart(long id, long user, String savedDate, double totalPrice, ArrayList<Item> items) {
		super();
		this.id = id;
		this.user = user;
		this.savedDate = savedDate;
		this.totalPrice = totalPrice;
		this.items = items;
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
	public String getSavedDate() {
		return savedDate;
	}
	public void setSavedDate(String savedDate) {
		this.savedDate = savedDate;
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

}
