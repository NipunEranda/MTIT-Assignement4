package com.sliit.abc.model;

public class Login {

	private long loginId;
	private long loginRole;
	private String email;
	private String password;

	public Login(long loginId, long loginRole, String email, String password) {
		super();
		this.loginId = loginId;
		this.loginRole = loginRole;
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

}
