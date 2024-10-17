package com.example.mygrocerystore.models;

import com.google.type.DateTime;
import java.math.BigDecimal;
import java.util.List;

public class MyOrdersModel {
    private String OrderId;
    private String UserId;
    private DateTime CreatedAt;
    private String MobileNumber;
    private String UserName;
    private String Status;
    private BigDecimal TotalAmount;
    private String ShippingAddress;
    private List<String> OrderItemIds;  // List of order item IDs (String)

    public MyOrdersModel() {
    }

    public MyOrdersModel(String orderId, String userName, String mobileNumber,
                         DateTime createdAt, String userId, BigDecimal totalAmount,
                         String shippingAddress, List<String> orderItemIds, String status) {
        OrderId = orderId;
        UserName = userName;
        MobileNumber = mobileNumber;
        CreatedAt = createdAt;
        UserId = userId;
        TotalAmount = totalAmount;
        ShippingAddress = shippingAddress;
        OrderItemIds = orderItemIds;
        Status = String.valueOf(OrderStatus.Pending);  // Default to Pending
    }

    // Getters and Setters
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public DateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        CreatedAt = createdAt;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public List<String> getOrderItemIds() {
        return OrderItemIds;
    }

    public void setOrderItemIds(List<String> orderItemIds) {
        OrderItemIds = orderItemIds;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    // Enum for order status
    public enum OrderStatus {
        Pending,
        PartiallyDelivered,
        Cancelled,
        Delivered
    }
}
