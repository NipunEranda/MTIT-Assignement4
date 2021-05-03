package com.sliit.abc.services;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserServiceImp implements UserService {

	DBManager db = DBManager.getInstance();

	@Override
	public JSONObject registerCustomer(String firstName, String lastName, String initials, String dob, String phoneNo,
			String gender, String address, String email, String password, String companyName) {
		return DBManager.CustomerClass.registerCustomer(firstName, lastName, initials, dob, phoneNo, gender, address,
				email, password, String.valueOf(3), companyName);
	}

	@Override
	public JSONObject activateAccount(String email, String code) {
		return DBManager.LoginClass.emailConfirmation(email, code);
	}

	@Override
	public JSONObject updateCustomerDetails(String firstName, String lastName, String initials, String dob,
			String phoneNo, String gender, String address, String companyName,String id) {
		DBManager.UserClass.updateUserDetails(id, firstName, lastName, initials, dob, phoneNo, gender, address);
		return DBManager.CustomerClass.updateCustomerDetails(companyName, id);
	}

	@Override
	public JSONObject deleteCustomer(String id) {
		return DBManager.CustomerClass.deleteCustomer(id);
	}

	@Override
	public JSONObject getCustomerDetails(String id) {
		return DBManager.CustomerClass.getCustomerById(id);
	}

	@Override
	public JSONArray getCustomerList() {
		return DBManager.CustomerClass.getAllCustomers();
	}

}
