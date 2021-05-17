package com.sliit.abc.controller;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sliit.abc.model.Customer;
import com.sliit.abc.model.Login;
import com.sliit.abc.model.ResetPassword;
import com.sliit.abc.services.UserService;
import com.sliit.abc.services.UserServiceImp;

@RestController
@EnableAutoConfiguration
public class UserManager {

	UserService us = new UserServiceImp();

	@PostMapping(value = "/customerRegistration", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String registerCustomer(@RequestBody Customer customer) {
		return us.registerCustomer(customer.getFirstName(), customer.getLastName(), customer.getInitials(),
				customer.getDob(), customer.getPhoneNo(), customer.getGender(), customer.getAddress(), customer.getLogin().getEmail(), customer.getLogin().getPassword()).toString();
	}
	
	@PostMapping(value = "/customerLogin", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String customerLogin(@RequestBody Login login) {
		return us.customerLogin(login.getEmail(), login.getPassword()).toString();
	}
	
	@PutMapping(value = "/updateCustomer", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String updateCustomer(@RequestBody Customer customer) {
		return us.updateCustomerDetails(customer.getFirstName(), customer.getLastName(), customer.getInitials(),
				customer.getDob(), customer.getPhoneNo(), customer.getGender(), customer.getAddress(), customer.getId()).toString();
	}
	
	@PutMapping(value = "/resetPassword", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String resetPassword(@RequestBody ResetPassword resetPassword) {
		return us.resetPassword(resetPassword.getCurrentPassword(), resetPassword.getNewPassword(), resetPassword.getEmail()).toString();
	}
	
	@DeleteMapping(value = "/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable String id) {
		return us.deleteCustomer(id).toString();
	}

	@GetMapping(value = "/")
	public String getAllCustomers() {
		return us.getCustomerList().toString();
	}

	@GetMapping(value = "/{id}")
	public String getCustomerById(@PathVariable String id) {
		return us.getCustomerDetails(id).toString();
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserManager.class, args);
	}

}
