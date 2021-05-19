package com.sliit.abc.services;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ProductService {
	
	// DELETE
	public JSONObject deleteItem(String id);

	// GET
	public JSONObject getItemDetails(String id);
	public JSONArray getItemList();

	//new POST
	public JSONObject registerItem(  String name,String itemType,  String description, String price);

	//new PUT
	public Object updateItemDetails(   String name,String itemType, String description, String price,Long id);

}
