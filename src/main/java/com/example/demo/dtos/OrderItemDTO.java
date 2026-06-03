package com.example.demo.dtos;

public class OrderItemDTO {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private String productName;     
    private float price;
    private int quantity;
    private int discount;          
    private float totalOrderItem;   

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer id, Integer orderId, Integer productId, String productName,
                        float price, int quantity, int discount, float totalOrderItem) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.totalOrderItem = totalOrderItem;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    public float getTotalOrderItem() { return totalOrderItem; }
    public void setTotalOrderItem(float totalOrderItem) { this.totalOrderItem = totalOrderItem; }
}