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

import com.example.demo.service.PromotionCategoryService;

@RestController
@RequestMapping({ "api/promotion-category" })
public class PromotionCategoryController {

	@Autowired
	private PromotionCategoryService promotionCategoryService;

	@PostMapping("ad/assign")
	public ResponseEntity<?> assign(@RequestParam Integer promotionId, @RequestParam Integer categoryId) {

		return promotionCategoryService.assignPromotionToCategory(promotionId, categoryId)
				? ResponseEntity.ok("Assigned successfully")
				: ResponseEntity.badRequest().body("Already exists");
	}

	@DeleteMapping("ad/remove")
	public ResponseEntity<?> remove(@RequestParam Integer promotionId, @RequestParam Integer categoryId) {

		promotionCategoryService.removePromotionFromCategory(promotionId, categoryId);
		return ResponseEntity.ok("Removed successfully");
	}

	@GetMapping("ad/find-by-promotion/{promotionId}")
	public ResponseEntity<?> findByPromotion(@PathVariable("promotionId") Integer promotionId) {
		return ResponseEntity.ok(promotionCategoryService.findByPromotion(promotionId));
	}

	@GetMapping("ad/find-by-category/{categoryId}")
	public ResponseEntity<?> findByCategory(@PathVariable("categoryId") Integer categoryId) {
		return ResponseEntity.ok(promotionCategoryService.findByCategory(categoryId));
	}
}
