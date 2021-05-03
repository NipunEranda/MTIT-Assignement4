package com.sliit.abc.model;

public class User {
	
	private long id;
	private String firstName;
	private String lastName;
	private String initials;
	private String dob;
	private String phoneNo;
	private char gender;
	private String address;
	
	public User() {
		
	}
	
	public User(long id, String firstName, String lastName, String initials, String dob, String phoneNo, char gender, String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.initials = initials;
		this.dob = dob;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getInitials() {
		return initials;
	}

	public String getDob() {
		return dob;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public char getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
