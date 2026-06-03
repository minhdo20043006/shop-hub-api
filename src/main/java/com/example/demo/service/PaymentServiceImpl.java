package com.example.demo.service;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.PaymentDTO;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Payment;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PayPalHttpClient client;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PaymentServiceImpl(
            @Value("${paypal.client.id}") String clientId,
            @Value("${paypal.client.secret}") String clientSecret,
            @Value("${paypal.mode:sandbox}") String mode) {

        if (clientId == null || clientId.trim().isEmpty() || "YOUR_SANDBOX_CLIENT_ID".equals(clientId)) {
            throw new IllegalArgumentException("paypal.client.id is missing or not set properly in application.properties!");
        }
        if (clientSecret == null || clientSecret.trim().isEmpty() || "YOUR_SANDBOX_CLIENT_SECRET".equals(clientSecret)) {
            throw new IllegalArgumentException("paypal.client.secret is missing or not set properly in application.properties!");
        }

        PayPalEnvironment environment = "live".equalsIgnoreCase(mode.trim())
                ? new PayPalEnvironment.Live(clientId, clientSecret)
                : new PayPalEnvironment.Sandbox(clientId, clientSecret);

        this.client = new PayPalHttpClient(environment);
        logger.info("PayPal client initialized in {} mode", mode);
    }

    @Override
    public String createPayPalOrder(Integer orderId, float amount) throws Exception {
        if (orderId == null || amount <= 0) {
            throw new IllegalArgumentException("Invalid orderId or amount");
        }

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown()
                .currencyCode("USD")
                .value(String.format("%.2f", amount));

        PurchaseUnitRequest purchaseUnit = new PurchaseUnitRequest()
                .referenceId(orderId.toString())
                .amountWithBreakdown(amountBreakdown);

        List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();
        purchaseUnits.add(purchaseUnit);
        orderRequest.purchaseUnits(purchaseUnits);

        // Cấu hình ApplicationContext
        ApplicationContext appContext = new ApplicationContext()
                .brandName("Shop Hub")
                .landingPage("NO_PREFERENCE")
                .userAction("PAY_NOW")
                // Nên dùng domain thực tế + port động, không hardcode localhost
                .returnUrl("http://localhost:6666/api/payment/paypal/success?orderId=" + orderId)
                .cancelUrl("http://localhost:6666/api/payment/paypal/cancel");

        orderRequest.applicationContext(appContext);

        OrdersCreateRequest request = new OrdersCreateRequest();
        request.requestBody(orderRequest);

        HttpResponse<com.paypal.orders.Order> response = client.execute(request);

        if (response.statusCode() == 201) {
            com.paypal.orders.Order createdOrder = response.result();
            for (LinkDescription link : createdOrder.links()) {
                if ("approve".equals(link.rel())) {
                    logger.info("PayPal order created successfully: {}", createdOrder.id());
                    return link.href();
                }
            }
        }

        logger.error("Failed to create PayPal order. Status: {}, Response: {}", response.statusCode(), response);
        throw new RuntimeException("Tạo PayPal Order thất bại. Status: " + response.statusCode());
    }

    @Override
    public boolean capturePayPalOrder(String paypalOrderId) throws Exception {
        if (paypalOrderId == null || paypalOrderId.trim().isEmpty()) {
            throw new IllegalArgumentException("PayPal orderId is required");
        }

        OrdersCaptureRequest request = new OrdersCaptureRequest(paypalOrderId);
        request.requestBody(new OrderRequest());

        HttpResponse<com.paypal.orders.Order> response = client.execute(request);

        if (response.statusCode() == 201) {
            logger.info("PayPal order captured successfully: {}", paypalOrderId);
            return true;
        }

        logger.error("Failed to capture PayPal order {}. Status: {}", paypalOrderId, response.statusCode());
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePayment(PaymentDTO paymentDTO) {
        try {
            if (paymentDTO == null || paymentDTO.getOrderId() == null) {
                throw new IllegalArgumentException("Invalid PaymentDTO or missing orderId");
            }

            Orders order = orderRepository.findById(paymentDTO.getOrderId())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + paymentDTO.getOrderId()));

            Payment payment = modelMapper.map(paymentDTO, Payment.class);
            payment.setOrder(order);
            payment.setCreatedAt(new Date());
            payment.setUpdatedAt(new Date());

            // Có thể set thêm status nếu cần (ví dụ: "SUCCESS" sau khi capture thành công)
            // payment.setStatus("SUCCESS");

            paymentRepository.save(payment);
            logger.info("Payment saved successfully for orderId: {}", paymentDTO.getOrderId());
            return true;

        } catch (Exception e) {
            logger.error("Error saving payment for orderId {}: {}", paymentDTO.getOrderId(), e.getMessage(), e);
            throw e;  // Ném lại để @Transactional rollback
        }
    }
}