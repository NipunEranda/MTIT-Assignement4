package com.sliit.abc.services;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UserService {

	// POST
	public JSONObject registerCustomer(String firstName, String lastName, String initials, String dob, String phoneNo,
			String gender, String address, String email, String password);
	
	public JSONObject customerLogin(String email, String password);

	// PUT
	public JSONObject updateCustomerDetails(String firstName, String lastName, String initials, String dob,
			String phoneNo, String gender, String address, String id);
	
	public JSONObject resetPassword(String currentPassword, String newPassowrd, String email);

	// DELETE
	public JSONObject deleteCustomer(String id);

	// GET
	public JSONObject getCustomerDetails(String id);

	
	public JSONArray getCustomerList();

}
