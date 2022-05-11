package com.emp.model.service;

public class EmpLoginRes {
	private String token;
	private String identity;
	public EmpLoginRes(String token,String identity) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
}
