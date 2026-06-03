package com.example.demo.dtos;

import java.util.List;

public class LoginResponseDTO {
	private String accessToken;
	private String refreshToken;
	private List<String> roles;
	private AccountInfoDTO account;

	public LoginResponseDTO() {
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public AccountInfoDTO getAccount() {
		return account;
	}

	public void setAccount(AccountInfoDTO account) {
		this.account = account;
	}
}
