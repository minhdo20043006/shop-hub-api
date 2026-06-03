package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.NotificationDTO;
import com.example.demo.service.NotificationService;

@RestController
@RequestMapping({ "api/notification" })
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/all/list/{accountId}")
	public List<NotificationDTO> getAllByAccount(@PathVariable Integer accountId) {
		return notificationService.findByAccount(accountId);
	}

	@GetMapping("/all/unread/{accountId}")
	public List<NotificationDTO> getUnread(@PathVariable Integer accountId) {
		return notificationService.findUnreadByAccount(accountId);
	}

	@PutMapping("/all/read/{notificationId}")
	public boolean markAsRead(@PathVariable Integer notificationId) {
		return notificationService.markAsRead(notificationId);
	}

	@PutMapping("/all/read-all/{accountId}")
	public boolean markAllAsRead(@PathVariable Integer accountId) {
		return notificationService.markAllAsRead(accountId);
	}

	@PostMapping("/create")
	public boolean create(@RequestBody NotificationDTO notificationDTO) {
		return notificationService.create(notificationDTO);
	}

	@GetMapping(value = "all/find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<NotificationDTO> findById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<NotificationDTO>(notificationService.findById(id), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<NotificationDTO>(HttpStatus.BAD_REQUEST);
		}
	}

}
