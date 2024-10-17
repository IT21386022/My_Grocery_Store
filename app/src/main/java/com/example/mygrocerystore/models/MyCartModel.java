package com.example.mygrocerystore.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {
//    String ProductId;
//    String ProductName;
//    int ProductPrice;
//    int Quantity;
//    String VendorId;
//    String VendorName;
//    String FulfillmentStatus;
//    String Amount;
//    String ShippingAddress;
//    String CreatedAt;

    private String productName;
    private double productPrice;
    private int totalQuantity;
    private double totalPrice;

//    public MyCartModel() {
//    }
//
//    public MyCartModel(String productId, String productName, int productPrice, int quantity, String vendorId, String vendorName, String fulfillmentStatus, String amount, String shippingAddress, String createdAt) {
//        ProductId = productId;
//        ProductName = productName;
//        ProductPrice = productPrice;
//        Quantity = quantity;
//        VendorId = vendorId;
//        VendorName = vendorName;
//        FulfillmentStatus = fulfillmentStatus;
//        Amount = amount;
//        ShippingAddress = shippingAddress;
//        CreatedAt = createdAt;
//    }
//
//    public MyCartModel(String productName, int productPrice, int totalQuantity, int totalPrice) {
//    }
//
//    public String getProductId() {
//        return ProductId;
//    }
//
//    public void setProductId(String productId) {
//        ProductId = productId;
//    }
//
//    public String getProductName() {
//        return ProductName;
//    }
//
//    public void setProductName(String productName) {
//        ProductName = productName;
//    }
//
//    public int getProductPrice() {
//        return ProductPrice;
//    }
//
//    public void setProductPrice(int productPrice) {
//        ProductPrice = productPrice;
//    }
//
//    public int getQuantity() {
//        return Quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        Quantity = quantity;
//    }
//
//    public String getVendorId() {
//        return VendorId;
//    }
//
//    public void setVendorId(String vendorId) {
//        VendorId = vendorId;
//    }
//
//    public String getVendorName() {
//        return VendorName;
//    }
//
//    public void setVendorName(String vendorName) {
//        VendorName = vendorName;
//    }
//
//    public String getFulfillmentStatus() {
//        return FulfillmentStatus;
//    }
//
//    public void setFulfillmentStatus(String fulfillmentStatus) {
//        FulfillmentStatus = fulfillmentStatus;
//    }
//
//    public String getAmount() {
//        return Amount;
//    }
//
//    public void setAmount(String amount) {
//        Amount = amount;
//    }
//
//    public String getShippingAddress() {
//        return ShippingAddress;
//    }
//
//    public void setShippingAddress(String shippingAddress) {
//        ShippingAddress = shippingAddress;
//    }
//
//    public String getCreatedAt() {
//        return CreatedAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        CreatedAt = createdAt;
//    }


    public MyCartModel() {
    }

    public MyCartModel(double totalPrice, int totalQuantity, double productPrice, String productName) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
