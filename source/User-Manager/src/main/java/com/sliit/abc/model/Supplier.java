package com.sliit.abc.model;

public class Supplier extends User{
	
	private Login login;
	
	public Supplier(long id, String firstName, String lastName, String initials, String dob, String phoneNo, String gender, String address, Login login) {
		super(id, firstName, lastName, initials, dob, phoneNo, gender, address);
		this.login = login;
	}

}
