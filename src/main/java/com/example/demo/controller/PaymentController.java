package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/api/payment/paypal")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

  
    @GetMapping("/create")
    public ResponseEntity<String> createPayment(
            @RequestParam Integer orderId,
            @RequestParam float amount) {
        try {
            String approveUrl = paymentService.createPayPalOrder(orderId, amount);
            return ResponseEntity.ok(approveUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi tạo PayPal order: " + e.getMessage());
        }
    }

   
    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(
            @RequestParam("token") String orderId) {  
        try {
            boolean success = paymentService.capturePayPalOrder(orderId);
            if (success) {
             
                return ResponseEntity.ok("Thanh toán thành công! Order ID: " + orderId);
            }
            return ResponseEntity.badRequest().body("Capture thanh toán thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi capture: " + e.getMessage());
        }
    }

  
    @GetMapping("/cancel")
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.ok("Thanh toán bị hủy bởi người dùng.");
    }
}