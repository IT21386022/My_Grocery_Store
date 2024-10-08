package com.example.mygrocerystore.models;

public class FeedbackModel {
    private String FirstName;
    private String LastName;
    private String CustomerFeedbackText;
    private int Rating;
    private String userId;  // To store the user's ID (optional)

    // Default constructor (required for Firestore)
    public FeedbackModel() {
    }

    public FeedbackModel(String firstName, String userId, int rating, String customerFeedbackText, String lastName) {
        FirstName = firstName;
        this.userId = userId;
        Rating = rating;
        CustomerFeedbackText = customerFeedbackText;
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCustomerFeedbackText() {
        return CustomerFeedbackText;
    }

    public void setCustomerFeedbackText(String customerFeedbackText) {
        CustomerFeedbackText = customerFeedbackText;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
