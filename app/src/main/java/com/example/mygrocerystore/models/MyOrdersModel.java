package com.example.mygrocerystore.models;

import java.util.List;

public class MyOrdersModel {
    private String id;
    private String productName;
    private String productPrice;
    private String currentDate;
    private String status;
    private List<MyCartModel> cartItems;

    public MyOrdersModel() {
    }

    public MyOrdersModel(String id,String productName, String productPrice, String currentDate, String status, List<MyCartModel> cartItems) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.currentDate = currentDate;
        this.status = status;
        this.cartItems = cartItems;
    }

    public MyOrdersModel(String productName, String productPrice, String currentDate, String id, String status) {
    }


    public String getId() {
        return id;
    }

    public void setOrderId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setOrderDate(String orderDate) {
        this.currentDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MyCartModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<MyCartModel> cartItems) {
        this.cartItems = cartItems;
    }
}
