package com.rgukt.in.Response;

public class AuthResponse {
	private String jwt;
	private boolean status;
	private String message;
	public AuthResponse() {}
	public AuthResponse(String jwt, boolean status, String message) {
		super();
		this.jwt = jwt;
		this.status = status;
		this.message = message;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
