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

import com.example.demo.service.PromotionProductService;

@RestController
@RequestMapping({ "api/promotion-product" })
public class PromotionProductController {
	@Autowired
	private PromotionProductService promotionProductService;

	// Gán promotion cho product
	@PostMapping("ad/assign")
	public ResponseEntity<?> assign(@RequestParam Integer promotionId, @RequestParam Integer productId) {

		return promotionProductService.assignPromotionToProduct(promotionId, productId)
				? ResponseEntity.ok("Assigned successfully")
				: ResponseEntity.badRequest().body("Already exists");
	}

	// Gỡ promotion khỏi product
	@DeleteMapping("ad/remove")
	public ResponseEntity<?> remove(@RequestParam Integer promotionId, @RequestParam Integer productId) {

		promotionProductService.removePromotionFromProduct(promotionId, productId);
		return ResponseEntity.ok("Removed successfully");
	}

	// Xem product thuộc promotion nào
	@GetMapping("ad/find-by-promotion/{promotionId}")
	public ResponseEntity<?> findByPromotion(@PathVariable("promotionId") Integer promotionId) {
		return ResponseEntity.ok(promotionProductService.findByPromotion(promotionId));
	}

	// Xem promotion của product
	@GetMapping("ad/find-by-product/{productId}")
	public ResponseEntity<?> findByProduct(@PathVariable("productId") Integer productId) {
		return ResponseEntity.ok(promotionProductService.findByProduct(productId));
	}
}
