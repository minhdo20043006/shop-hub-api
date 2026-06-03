package com.example.demo.service;

import com.example.demo.dtos.PaymentDTO;

public interface PaymentService {
    String createPayPalOrder(Integer orderId, float amount) throws Exception;

    boolean capturePayPalOrder(String orderId) throws Exception;

    boolean savePayment(PaymentDTO paymentDTO);
}