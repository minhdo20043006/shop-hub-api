package com.example.demo.dtos;

import com.example.demo.enums.StatusAccount;

public class LoginRequestDTO {
	private String username;
    private String password;
    private StatusAccount status;
    
	public StatusAccount getStatus() {
		return status;
	}
	public void setStatus(StatusAccount status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginRequestDTO(String username, String password,StatusAccount status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}
	public LoginRequestDTO() {
		super();
	}
    
    

}
