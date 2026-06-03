package com.example.demo.enums;

public enum PaymentStatus {
	UNPAID, // Chưa thanh toán (mới tạo order)
	PENDING, // Đang chờ cổng thanh toán 
	PAID, // Đã thanh toán thành công
	FAILED, // Thanh toán thất bại
	CANCELED, // Người dùng hủy thanh toán
	REFUNDED // Đã hoàn tiền
}
