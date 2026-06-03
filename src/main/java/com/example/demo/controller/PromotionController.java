package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.PromotionDTO;
import com.example.demo.dtos.PromotionUpdateStatusDTO;
import com.example.demo.enums.ProductStatus;
import com.example.demo.enums.PromotionStatus;
import com.example.demo.service.PromotionService;

@RestController
@RequestMapping({ "api/promotion" })
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	// user
	@GetMapping(value = "all/find-all-promotion-active", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDTO>> findAllByStatusActive(PromotionStatus statusPromotion) {
		try {
			return new ResponseEntity<List<PromotionDTO>>(promotionService.findAllActive(statusPromotion.ACTIVE),
					HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// admin
	@GetMapping(value = "ad/find-all-for-admin", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDTO>> findAllForAdmin() {
		try {
			return new ResponseEntity<List<PromotionDTO>>(promotionService.findAllForAdmin(), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// localhost:6666/api/promotion/ad/find-by-status-for-admin?statusPromotion=ACTIVE
	@GetMapping(value = "ad/find-by-status-for-admin", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDTO>> findByStatusForAdmin(@RequestParam PromotionStatus statusPromotion) {
		try {
			return new ResponseEntity<List<PromotionDTO>>(promotionService.findByStatusForAdmin(statusPromotion),
					HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// admin
	@GetMapping(value = "ad/find-by-product-id/{productId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDTO>> findByProductIdForAdmin(@PathVariable("productId") Integer productId) {
		try {
			return new ResponseEntity<List<PromotionDTO>>(promotionService.findByIdProduct(productId), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// admin - user
	@GetMapping(value = "au/find-by-category-id/{categoryId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDTO>> findByCategoryIdForAdmin(@PathVariable("categoryId") Integer categoryId) {
		try {
			return new ResponseEntity<List<PromotionDTO>>(promotionService.findByIdCategory(categoryId), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	// user
	@GetMapping(value = "user/find-valid-promotion-by-product/{productId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PromotionDTO>> findValidPromotionByProduct(
			@PathVariable("productId") Integer productId) {
		try {
			return new ResponseEntity<List<PromotionDTO>>(promotionService.findValidPromotionByProduct(productId),
					HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<PromotionDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "ad/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Create(@RequestBody PromotionDTO promotionDTO) {
		try {
			if (promotionService.Create(promotionDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	
	@PutMapping(value = "ad/update/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Update(@RequestBody PromotionDTO promotionDTO, @PathVariable("id") Integer id) {
		try {
			if (promotionService.Update(id, promotionDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "ad/update-status-promotion", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> UpdateStatusPromotion(@RequestBody PromotionUpdateStatusDTO updateStatusDto,
			@RequestParam Integer id) {
		try {
			if (promotionService.UpdateStatus(id, updateStatusDto)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("ad/delete/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id) {

		boolean result = promotionService.Delete(id);

		if (!result) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
