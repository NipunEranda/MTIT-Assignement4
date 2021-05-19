package com.sliit.abc.model;

public class Customer extends User{

	private LoginResponse login;
	
	public Customer(long id, String firstName, String lastName, String initials, String dob, String phoneNo, String gender, String address, LoginResponse login) {
		super(id, firstName, lastName, initials, dob, phoneNo, gender, address);
		this.setLogin(login);
	}

	public LoginResponse getLogin() {
		return login;
	}

	public void setLogin(LoginResponse login) {
		this.login = login;
	}
	
}
