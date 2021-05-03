package com.sliit.abc.services;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UserService {

	// POST
	public JSONObject registerCustomer(String firstName, String lastName, String initials, String dob, String phoneNo,
			String gender, String address, String email, String password, String companyName);

	public JSONObject activateAccount(String email, String code);

	// PUT
	public JSONObject updateCustomerDetails(String firstName, String lastName, String initials, String dob,
			String phoneNo, String gender, String address, String companyName, String id);

	// DELETE
	public JSONObject deleteCustomer(String id);

	// GET
	public JSONObject getCustomerDetails(String id);

	// ADMIN
	public JSONArray getCustomerList();

}
