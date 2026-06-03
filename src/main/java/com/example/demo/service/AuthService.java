package com.example.demo.service;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.LoginRequestDTO;
import com.example.demo.dtos.LoginResponseDTO;


public interface AuthService {
	public LoginResponseDTO login(LoginRequestDTO request);
	public AccountDTO getCurrentAccount();


}
