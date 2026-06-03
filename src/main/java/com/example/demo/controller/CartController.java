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
import com.example.demo.dtos.CartDTO;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = "/{accountId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<CartDTO>> getCart(@PathVariable Integer accountId) {
        try {
            return new ResponseEntity<>(cartService.findByAccountId(accountId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> addToCart(@RequestBody CartDTO cartDTO) {
        try {
            if (cartService.addToCart(cartDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{id}", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> updateCartItem(@PathVariable Integer id, @RequestBody CartDTO cartDTO) {
        try {
            if (cartService.updateCartItem(id, cartDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/remove/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Void> removeCartItem(@PathVariable Integer id) {
        try {
            if (cartService.removeCartItem(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/total/{accountId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Float> getTotal(@PathVariable Integer accountId) {
        try {
            return new ResponseEntity<>(cartService.calculateTotal(accountId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}