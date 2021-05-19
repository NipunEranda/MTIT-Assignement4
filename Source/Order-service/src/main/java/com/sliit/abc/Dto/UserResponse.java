package com.sliit.abc.Dto;

public class UserResponse {

	private String id;
	private String firstName;
	private String lastName;
	private String initials;
	private String phoneNo;
	private String address;

	public UserResponse(String id, String firstName, String lastName, String initials, String phoneNo, String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.initials = initials;
		this.phoneNo = phoneNo;
		this.address = address;
	}

	public String getId() {
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setId(String id) {
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

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
