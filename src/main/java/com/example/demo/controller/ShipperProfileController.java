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
import com.example.demo.dtos.SellerProfileDTO;
import com.example.demo.dtos.SellerReviewDTO;
import com.example.demo.dtos.ShipperProfileDTO;
import com.example.demo.dtos.ShipperReviewDTO;
import com.example.demo.service.SellerProfileService;
import com.example.demo.service.ShipperProfileService;
import com.example.demo.service.ShipperReviewService;

@RestController
@RequestMapping({ "api/shipper" })
public class ShipperProfileController {

	@Autowired
	private ShipperProfileService shipperProfileService;

	@Autowired
	private ShipperReviewService shipperReviewService;

	@GetMapping(value = "find-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipperProfileDTO>> findAll() {
		try {
			return new ResponseEntity<List<ShipperProfileDTO>>(shipperProfileService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ShipperProfileDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Create(@RequestBody ShipperProfileDTO shipperProfileDTO) {
		try {
			if (shipperProfileService.Create(shipperProfileDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception  
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "profile/update/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> Update(@RequestBody ShipperProfileDTO shipperProfileDTO,
			@PathVariable("id") Integer id) {
		try {
			if (shipperProfileService.Update(id, shipperProfileDTO)) {
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
			if (shipperProfileService.Delete(id)) {
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
	public ResponseEntity<List<ShipperReviewDTO>> findAllReview() {
		try {
			return new ResponseEntity<List<ShipperReviewDTO>>(shipperReviewService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ShipperReviewDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "review/find-by-shipper-id/{shipperId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipperReviewDTO>> findBySellerIdForReview(
			@PathVariable("shipperId") Integer shipperId) {
		try {
			return new ResponseEntity<List<ShipperReviewDTO>>(
					shipperReviewService.findByShipperProfileIdForReview(shipperId), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<ShipperReviewDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "review/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> CreateReview(@RequestBody ShipperReviewDTO shipperReviewDTO) {
		try {
			if (shipperReviewService.Create(shipperReviewDTO)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "review/update/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> UpdateReview(@RequestBody ShipperReviewDTO shipperReviewDTO,
			@PathVariable("id") Integer id) {
		try {
			if (shipperReviewService.Update(id, shipperReviewDTO)) {
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
			if (shipperReviewService.Delete(id)) {
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
