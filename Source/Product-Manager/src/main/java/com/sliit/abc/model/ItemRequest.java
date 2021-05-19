package com.sliit.abc.model;

public class ItemRequest {

	private long id;
	private String name;
	private String description;
	private String itemType;
	private String price;

	public ItemRequest(long id, String name, String description, String itemType, String price) {
		super();
		this.id = id;

		this.name = name;
		this.description = description;
		this.itemType = itemType;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
