package com.example.mygrocerystore.models;

public class PopularModel {
    private String id;
    private String productName;
    private String description;
    private String imageUrl;
    private double productPrice;
    private int stock;
    private String vendorId;
    private String vendorName;
    private String createdAt;

    public PopularModel() {
    }

    public PopularModel(String id, String productName, String description, String imageUrl, double productPrice, int stock, String vendorId, String vendorName, String createdAt) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.productPrice = productPrice;
        this.stock = stock;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
