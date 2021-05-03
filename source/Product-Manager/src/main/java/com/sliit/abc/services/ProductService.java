package com.sliit.abc.services;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ProductService {
	
	//POST
	public JSONObject addItem(String name, String description, String itemType, String price);
	
	//PUT
	public JSONObject updateItem(String name, String description, String itemType, String price, String id);
	
	//DELETE
	public JSONObject deleteItem(String id);
	
	//GET
	public JSONArray getAllItems();
	public JSONObject getItemById(String id);
	public JSONArray getItemTypes();
	public JSONObject getItemTypeById(String id);

}
