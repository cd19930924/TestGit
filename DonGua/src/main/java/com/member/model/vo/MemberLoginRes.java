package com.member.model.vo;

public class MemberLoginRes {
	private String token;
	private String identity;
	public MemberLoginRes(String token,String identity) {
		this.token = token;
	}
	public MemberLoginRes() {
		// TODO Auto-generated constructor stub
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
