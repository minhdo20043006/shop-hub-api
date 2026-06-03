package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.dtos.LoginRequestDTO;
import com.example.demo.service.AccountService;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping({ "api/account" })
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	
	@GetMapping(value = "ad/find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountDTO>> findAll() {
		try {
			return new ResponseEntity<List<AccountDTO>>(accountService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<AccountDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "all/find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountDTO> findById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<AccountDTO>(accountService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<AccountDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "all/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Create(@RequestBody AccountDTO accountDTO) {
		try {
			if (accountService.Create(accountDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping(value = "all/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Update(@RequestBody AccountDTO accountDTO) {
		try {
			if (accountService.Update( accountDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "all/delete/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			if (accountService.Delete(id)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}
