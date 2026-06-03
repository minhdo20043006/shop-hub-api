package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.LoginRequestDTO;
import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Role;
import com.example.demo.entities.RoleAccount;

import com.example.demo.enums.StatusAccount;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleAccountRepository;
import com.example.demo.repository.RoleRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleAccountRepository roleAccountRepository;

	@Autowired
	private ModelMapper modelMapper;

	AccountServiceImpl(RoleAccountRepository roleAccountRepository) {
		this.roleAccountRepository = roleAccountRepository;
	}

	@Override
	public List<AccountDTO> findAll() {
		List<Account> accounts = accountRepository.findAll();
		return modelMapper.map(accounts, new TypeToken<List<AccountDTO>>() {
		}.getType());
	}

	@Transactional
	@Override
	public boolean Create(AccountDTO accountDTO) {
		try {
			if (accountRepository.existsByEmail(accountDTO.getEmail())) {
				return false;
			}
			if (accountRepository.existsByUsername(accountDTO.getUsername())) {
				return false;
			}
			Account account = modelMapper.map(accountDTO, Account.class);
			String hashedPassword = BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt(10));
			account.setPassword(hashedPassword);

			account.setAvatar("default.png");
			account.setCreatedAt(new Date());
			account.setUpdatedAt(new Date());
			account.setStatus(StatusAccount.ACTIVE);
			accountRepository.save(account);
			Role userRole = roleRepository.findByNameRole("USER")
					.orElseThrow(() -> new RuntimeException("Role USER not found"));

			RoleAccount roleAccount = new RoleAccount();
			roleAccount.setAccount(account);
			roleAccount.setRole(userRole);

			roleAccountRepository.save(roleAccount);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(AccountDTO accountDTO) {
		try {
			Account existingAccount = accountRepository.findById(accountDTO.getId()).orElse(null);

			if (existingAccount == null) {
				return false;
			}

			if (accountRepository.existsByEmailAndIdNot(accountDTO.getEmail(), accountDTO.getId())) {
				return false;
			}

			if (accountRepository.existsByUsernameAndIdNot(accountDTO.getUsername(), accountDTO.getId())) {
				return false;
			}

			
			existingAccount.setFullName(accountDTO.getFullName());
			existingAccount.setEmail(accountDTO.getEmail());
			existingAccount.setUsername(accountDTO.getUsername());
			existingAccount.setPhone(accountDTO.getPhone());
			existingAccount.setAddress(accountDTO.getAddress());
			existingAccount.setDob(accountDTO.getDob());
			existingAccount.setAvatar(accountDTO.getAvatar());
			existingAccount.setStatus(StatusAccount.ACTIVE);

			
			if (accountDTO.getPassword() != null && !accountDTO.getPassword().isBlank()) {
				String hashedPassword = BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt(10));
				existingAccount.setPassword(hashedPassword);
			}

			existingAccount.setUpdatedAt(new Date());

			accountRepository.save(existingAccount);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(int id) {
		Optional<Account> opt = accountRepository.findById(id);

		if (opt.isEmpty()) {
			return false;
		}

		Account account = opt.get();

		if (account.getStatus() == StatusAccount.DELETED) {
			return false;
		}

		account.setStatus(StatusAccount.DELETED);
		accountRepository.save(account);

		return true;
	}

	@Override
	public void addRoleToAccount(String username, String roleName) {
		Account account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		Role role = roleRepository.findByNameRole(roleName).orElseThrow(() -> new RuntimeException("Role not found"));

		RoleAccount roleAccount = new RoleAccount();
		roleAccount.setAccount(account);
		roleAccount.setRole(role);
		roleAccountRepository.save(roleAccount);
	}

	@Override
	public AccountDTO findById(Integer id) {
		Account account = accountRepository.findById(id).get();
		return modelMapper.map(account, AccountDTO.class);
	}

}
