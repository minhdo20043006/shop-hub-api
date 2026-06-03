package com.example.demo.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.LoginRequestDTO;
import com.example.demo.dtos.LoginResponseDTO;
import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.AccountInfoDTO;
import com.example.demo.entities.Account;
import com.example.demo.enums.StatusAccount;
import com.example.demo.repository.AccountRepository;
import com.example.demo.security.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public LoginResponseDTO login(LoginRequestDTO request) {

		Account account = accountRepository.findByUsername(request.getUsername()).orElse(null);
		if (account == null) {
			throw new RuntimeException("Sai username hoặc password");
		}
		if (!BCrypt.checkpw(request.getPassword(), account.getPassword())) {
			throw new RuntimeException("Sai username hoặc password");
		}
		if (account.getStatus() == StatusAccount.DELETED || account.getStatus() == StatusAccount.BANNED
				|| account.getStatus() == StatusAccount.INACTIVE) {
			throw new RuntimeException("TAI KHOANG DA BI VO HIEU HOA");
		}
		AccountInfoDTO accountInfoDTO = modelMapper.map(account, AccountInfoDTO.class);
		List<String> roles = account.getRoleAccounts().stream().map(ra -> ra.getRole().getNameRole()).toList();

		String accessToken = jwtUtil.generateAccessToken(account);
		String refreshToken = "COMING SOON";

		LoginResponseDTO response = new LoginResponseDTO();
		response.setAccessToken(accessToken);
		response.setRefreshToken(refreshToken);
		response.setRoles(roles);
		response.setAccount(accountInfoDTO);
		return response;
	}

	@Override
	public AccountDTO getCurrentAccount() {

		var authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| "anonymousUser".equals(authentication.getPrincipal())) {

			throw new RuntimeException("Chưa đăng nhập");
		}

		String username = authentication.getName();

		Account account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy account"));

		AccountDTO dto = new AccountDTO();
		dto.setId(account.getId());
		dto.setUsername(account.getUsername());
		dto.setFullName(account.getFullName());
		dto.setEmail(account.getEmail());
		dto.setPhone(account.getPhone());
		dto.setAddress(account.getAddress());
		dto.setAvatar(account.getAvatar());
		dto.setDob(account.getDob());

		return dto;
	}



}
