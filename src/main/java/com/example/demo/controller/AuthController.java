package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.LoginRequestDTO;
import com.example.demo.dtos.LoginResponseDTO;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping(value = "login", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request, HttpSession session) {

		try {
			LoginResponseDTO response = authService.login(request);

			if (response != null) {
				session.setAttribute("ACCOUNT", response.getAccount());
				return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.OK);
			}
			return new ResponseEntity<LoginResponseDTO>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<LoginResponseDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/me")
	public ResponseEntity<?> me() {
		try {
			return ResponseEntity.ok(authService.getCurrentAccount());
		} catch (RuntimeException e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
	}

}
