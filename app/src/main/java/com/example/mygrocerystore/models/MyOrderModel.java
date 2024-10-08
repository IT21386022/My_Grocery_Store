package com.example.mygrocerystore.models;

import java.util.List;

public class MyOrderModel {
    private String userName;
    private String userMobile;
    private String userAddress;
    private String productNames;
    private String status;
    private String currentDate;
    private String totalPrices;
    String id;

    private List<MyCartModel> cartItems;

    public MyOrderModel() {
    }

    public MyOrderModel(List<MyCartModel> cartItems, String id, String totalPrices, String currentDate, String status, String productNames, String userAddress, String userMobile, String userName) {
        this.cartItems = cartItems;
        this.id = id;
        this.totalPrices = totalPrices;
        this.currentDate = currentDate;
        this.status = status;
        this.productNames = productNames;
        this.userAddress = userAddress;
        this.userMobile = userMobile;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(String totalPrices) {
        this.totalPrices = totalPrices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MyCartModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<MyCartModel> cartItems) {
        this.cartItems = cartItems;
    }
}
