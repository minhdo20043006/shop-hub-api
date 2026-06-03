package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PromotionAccountService;
import com.example.demo.service.PromotionProductService;

@RestController
@RequestMapping({ "api/promotion-account" })
public class PromotionAccountController {
	@Autowired
	private PromotionAccountService promotionAccountService;

	@PostMapping("ad/assign")
	public ResponseEntity<?> assign(@RequestParam Integer promotionId, @RequestParam Integer accountId) {

		return promotionAccountService.assignPromotionToAccount(promotionId, accountId)
				? ResponseEntity.ok("Assigned successfully")
				: ResponseEntity.badRequest().body("Already exists");
	}

	@DeleteMapping("ad/remove")
	public ResponseEntity<?> remove(@RequestParam Integer promotionId, @RequestParam Integer accountId) {

		promotionAccountService.removePromotionFromAccount(promotionId, accountId);
		return ResponseEntity.ok("Removed successfully");
	}

	@GetMapping("ad/find-by-account/{accountId}")
	public ResponseEntity<?> findByAccount(@PathVariable Integer accountId) {
		return ResponseEntity.ok(promotionAccountService.findByAccount(accountId));
	}

	@GetMapping("ad/valid/{accountId}")
	public ResponseEntity<?> findValid(@PathVariable Integer accountId) {
		return ResponseEntity.ok(promotionAccountService.findValidPromotionByAccount(accountId));
	}
}
