# 🛍️ Shop Hub — REST API

Backend API cho ứng dụng thương mại điện tử Shop Hub, 
xây dựng bằng Spring Boot.

## 📌 Giới thiệu

Shop Hub là hệ thống thương mại điện tử hỗ trợ 3 vai trò: 
**Khách hàng**, **Người bán (Seller)** và **Shipper**, 
với đầy đủ tính năng từ duyệt sản phẩm đến thanh toán 
và theo dõi đơn hàng.

## ⚙️ Công nghệ sử dụng

- Java 17 + Spring Boot 3
- Spring Security + JWT Authentication
- WebSocket (Chat realtime)
- Spring Data JPA + MySQL
- Maven

## 🚀 Tính năng chính

- Đăng ký / Đăng nhập với JWT
- Quản lý sản phẩm, danh mục, hình ảnh sản phẩm
- Giỏ hàng và đặt hàng
- Thanh toán đơn hàng
- Chat realtime giữa buyer và seller
- Hệ thống thông báo real-time
- Quản lý khuyến mãi (theo sản phẩm, danh mục, tài khoản)
- Phân quyền Admin / Seller / Shipper / Customer

## 🗂️ Cấu trúc project
src/
├── controller/    # REST API endpoints
├── service/       # Business logic
├── repository/    # Database queries
├── entity/        # Database models
├── security/      # JWT & Spring Security
└── configuration/ # App configuration
## ▶️ Cách chạy project

1. Clone repo về máy
2. Tạo database MySQL tên `shop_hub`
3. Mở file `application.properties`, 
   cập nhật thông tin database
4. Chạy lệnh: `mvn spring-boot:run`
5. API chạy tại: `http://localhost:8080`

## 🔗 Các repo liên quan

- [Shop Hub Admin](https://github.com/[minhdo20043006]/shop-hub-admin) 
  — Trang quản trị web (Spring MVC)
- [Shop Hub Mobile](https://github.com/[minhdo20043006]/shop-hub-mobile) 
  — Ứng dụng mobile (Flutter)


## 👨‍💻 Nhóm phát triển

**Đỗ Quốc Minh** (minhdo20043006) — Fullstack Developer  
Dự án nhóm — Computing Project, Aptech 2026
