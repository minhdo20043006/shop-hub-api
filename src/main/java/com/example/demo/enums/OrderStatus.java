package com.example.demo.enums;

public enum OrderStatus {
	CREATED, // Đơn vừa được tạo (chưa thanh toán)
	CONFIRMED, // Đã thanh toán, chờ xử lý
	PROCESSING, // Đang chuẩn bị hàng
	SHIPPING, // Đang giao
	DELIVERED, // Giao thành công
	COMPLETED, // Hoàn tất (buyer xác nhận)
	CANCELED, // Hủy trước khi giao
	RETURNED // Trả hàng / hoàn đơn
}
