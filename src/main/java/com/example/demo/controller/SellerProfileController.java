package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.ProductSoldDTO;
import com.example.demo.dtos.SellerApprovedStatusDTO;
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.dtos.SellerStatusDTO;
import com.example.demo.entities.SellerProfile;
import com.example.demo.enums.ApprovedStatus;
import com.example.demo.service.OrderItemSrevice;
import com.example.demo.service.SellerProfileService;
import com.example.demo.service.SellerReviewService;

@RestController
@RequestMapping({ "api/seller" })
public class SellerProfileController {

	@Autowired
	private SellerReviewService sellerReviewService;
	@Autowired
	private SellerProfileService sellerProfileService;
	@Autowired
	private OrderItemSrevice orderItemService;

	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerProfileDTO>> findAll() {
		try {
			return new ResponseEntity<List<SellerProfileDTO>>(sellerProfileService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<SellerProfileDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Create(@RequestBody SellerProfileDTO sellerProfileDTO) {
		try {
			if (sellerProfileService.Create(sellerProfileDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "profile/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Update(@RequestBody SellerProfileDTO sellerProfileDTO) {
		try {
			if (sellerProfileService.Update(sellerProfileDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "profile/delete/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			if (sellerProfileService.Delete(id)) {
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

	@GetMapping(value = "review/findall", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerReviewDTO>> findAllReview() {
		try {
			return new ResponseEntity<List<SellerReviewDTO>>(sellerReviewService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<SellerReviewDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "review/find-by-seller-id/{sellerId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerReviewDTO>> findBySellerIdForReview(@PathVariable("sellerId") Integer sellerId) {
		try {
			return new ResponseEntity<List<SellerReviewDTO>>(
					sellerReviewService.findBySellerProfileIdForReview(sellerId), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<SellerReviewDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "review/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> CreateReview(@RequestBody SellerReviewDTO sellerReviewDTO) {
		try {
			if (sellerReviewService.Create(sellerReviewDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "review/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> UpdateReview(@RequestBody SellerReviewDTO sellerReviewDTO) {
		try {
			if (sellerReviewService.Update(sellerReviewDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "review/delete/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteReview(@PathVariable("id") int id) {
		try {
			if (sellerReviewService.Delete(id)) {
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

	@GetMapping(value = "exists-by-account/{accountId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> existsByAccountId(@PathVariable("accountId") Integer accountId) {
		try {
			boolean exists = sellerProfileService.existsByAccountId(accountId);
			return new ResponseEntity<>(exists, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "status-by-account/{accountId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerStatusDTO> getSellerStatus(@PathVariable Integer accountId) {

		try {
			return new ResponseEntity<SellerStatusDTO>(sellerProfileService.getSellerStatusByAccountId(accountId),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SellerStatusDTO>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "find-by-account-id/{accountId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerProfileDTO> findById(@PathVariable("accountId") Integer accountId) {
		try {
			return new ResponseEntity<SellerProfileDTO>(sellerProfileService.findByAccountId(accountId), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<SellerProfileDTO>(HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/products-sold/{sellerId}")
	public ResponseEntity<?> getProductsSold(@PathVariable Integer sellerId) {
		List<ProductSoldDTO> result = orderItemService.getProductsSoldBySeller(sellerId);

		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/find-by-status/{status}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerProfileDTO>> findByStatus(@PathVariable("status") ApprovedStatus status) {
		try {
			List<SellerProfileDTO> sellers = sellerProfileService.findByStatus(status);

			return new ResponseEntity<>(sellers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "profile/update-status/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateSellerStatus(@PathVariable("id") Integer id,
			@RequestBody SellerApprovedStatusDTO statusDto) {

		try {
			boolean updated = sellerProfileService.updateStatusSeller(id, statusDto);

			if (updated) {
				return new ResponseEntity<>(HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "find-by-id/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<SellerProfileDTO> findByIdSeller(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<SellerProfileDTO>(sellerProfileService.findById(id), HttpStatus.OK);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<SellerProfileDTO>(HttpStatus.BAD_REQUEST);
		}
	}

}
