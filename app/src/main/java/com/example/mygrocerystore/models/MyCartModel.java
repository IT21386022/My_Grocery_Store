package com.example.mygrocerystore.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String ProductId;
    String ProductName;
    String ProductPrice;
    String Quantity;
    String VendorId;
    String VendorName;
    String FulfillmentStatus;
    String Amount;
    String ShippingAddress;
    String CreatedAt;

    public MyCartModel(String productId, String productName, String productPrice, String quantity, String vendorId, String vendorName, String amount, String shippingAddress, String createdAt) {
        ProductId = productId;
        ProductName = productName;
        ProductPrice = productPrice;
        Quantity = quantity;
        VendorId = vendorId;
        VendorName = vendorName;
        FulfillmentStatus = String.valueOf(FulfillmentStatusEnum.Pending);
        Amount = amount;
        ShippingAddress = shippingAddress;
        CreatedAt = createdAt;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getVendorId() {
        return VendorId;
    }

    public void setVendorId(String vendorId) {
        VendorId = vendorId;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getFulfillmentStatus() {
        return FulfillmentStatus;
    }

    public void setFulfillmentStatus(String fulfillmentStatus) {
        FulfillmentStatus = fulfillmentStatus;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public enum FulfillmentStatusEnum
    {
        Pending,
        Delivered,
        PartiallyDelivered,
        Shipped,
    }
}
