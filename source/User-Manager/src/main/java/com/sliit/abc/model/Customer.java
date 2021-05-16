package com.sliit.abc.model;

public class Customer extends User{

	private Login login;
	
	public Customer(long id, String firstName, String lastName, String initials, String dob, String phoneNo, String gender, String address, Login login) {
		super(id, firstName, lastName, initials, dob, phoneNo, gender, address);
		this.setLogin(login);
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
}
