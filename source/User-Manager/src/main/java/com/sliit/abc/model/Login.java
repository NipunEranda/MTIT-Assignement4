package com.sliit.abc.model;

public class Login {

	private long loginId;
	private long loginRole;
	private String email;
	private String password;
	private String newPassword;

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public long getLoginRole() {
		return loginRole;
	}

	public void setLoginRole(long loginRole) {
		this.loginRole = loginRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
