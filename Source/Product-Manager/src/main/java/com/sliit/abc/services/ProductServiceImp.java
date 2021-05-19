package com.sliit.abc.services;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductServiceImp implements ProductService {

	DBManager db = DBManager.getInstance();

	@Override
	public JSONObject deleteItem(String id) {
		return DBManager.ItemClass.deleteItem(id);
	}

	@Override
	public JSONObject getItemDetails(String id) {
		return new JSONObject(DBManager.ItemClass.getItemById(id));
	}

	@Override
	public JSONArray getItemList() {
		return DBManager.ItemClass.getAllItems();
	}

	@Override
	public JSONObject registerItem(String name, String itemType, String description, String price) {
		return DBManager.ItemClass.registerItem(name, itemType, description, price);

	}

	@Override
	public Object updateItemDetails(String name, String itemType, String description, String price, Long id) {
		DBManager.ItemClass.updateItemDetails(String.valueOf(id), name, itemType, description, price);
		return DBManager.ItemClass.updateItemDetails(String.valueOf(id), name, itemType, description, price);
	}

}
