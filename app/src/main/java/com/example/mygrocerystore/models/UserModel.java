package com.example.mygrocerystore.models;

public class UserModel {
//    String name;
//    String email;
//    String password;
//    String number;
//    String address;
//    String profileImg;
//
//    public UserModel(){
//    }
//
//    public UserModel(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getProfileImg() {
//        return profileImg;
//    }
//
//    public void setProfileImg(String profileImg) {
//        this.profileImg = profileImg;
//    }

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String profileImg;
    private String role;
    private String token;
    private String message;
    private String address;


    public UserModel() {
    }

    public UserModel(String firstname, String lastname, String email, String password, String phone, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public UserModel(String firstname, String lastname, String email, String password, String phone, String profileImg, String role, String token, String message, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.profileImg = profileImg;
        this.role = role;
        this.token = token;
        this.message = message;
        this.address = address;
    }

    public UserModel(String userFirstName, String userLastName, String userEmail, String userPassword, String userPhone) {
    }

    public UserModel(String userEmail, String userPassword) {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
