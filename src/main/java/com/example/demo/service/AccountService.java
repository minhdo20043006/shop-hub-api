package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.LoginRequestDTO;
import com.example.demo.dtos.AccountInfoDTO;

public interface AccountService {
	
	public List<AccountDTO> findAll();
	
	public boolean Create(AccountDTO accountDTO);
	
	public boolean Update(AccountDTO accountDTO);
	
	public boolean Delete(int id);
	
	public void addRoleToAccount(String username, String roleName);
	
	public AccountDTO findById(Integer id);
	
	
}
