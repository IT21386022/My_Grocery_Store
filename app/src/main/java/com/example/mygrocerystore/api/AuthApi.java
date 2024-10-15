package com.example.mygrocerystore.api;

import com.example.mygrocerystore.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/api/auth/register")
    Call<UserModel> registerUser(@Body UserModel userModel);
}
