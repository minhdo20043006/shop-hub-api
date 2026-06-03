package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dtos.ProductImagesDTO;
import com.example.demo.service.ProductImagesService;

@RestController
@RequestMapping({ "api/product-images" })
public class ProductImagesController {
	@Autowired
	private ProductImagesService productImagesService;

	// Upload nhiều ảnh
	@PostMapping("/upload/{productId}")
	public ResponseEntity<?> uploadImages(@PathVariable Integer productId,
			@RequestParam("files") List<MultipartFile> files) {

		boolean result = productImagesService.uploadImages(productId, files);

		if (result) {
			return ResponseEntity.ok("Upload images successfully");
		}
		return ResponseEntity.badRequest().body("Upload images failed");
	}

	// Set ảnh chính
	@PutMapping("/set-primary/{imageId}")
	public ResponseEntity<?> setPrimaryImage(@PathVariable Integer imageId) {

		boolean result = productImagesService.setPrimaryImage(imageId);

		if (result) {
			return ResponseEntity.ok("Set primary image successfully");
		}
		return ResponseEntity.badRequest().body("Set primary image failed");
	}

	// Xóa ảnh soft delete hoặc hard delete
	@DeleteMapping("/{imageId}")
	public ResponseEntity<?> deleteImage(@PathVariable Integer imageId) {

		boolean result = productImagesService.deleteImage(imageId);

		if (result) {
			return ResponseEntity.ok("Delete image successfully");
		}
		return ResponseEntity.badRequest().body("Delete image failed");
	}

	// Lấy danh sách ảnh theo product
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<ProductImagesDTO>> findByProduct(@PathVariable Integer productId) {

		return ResponseEntity.ok(productImagesService.findByProduct(productId));
	}
}
