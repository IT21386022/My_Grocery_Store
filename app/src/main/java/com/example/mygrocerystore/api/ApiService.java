package com.example.mygrocerystore.api;

import com.example.mygrocerystore.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/auth/login")
    Call<UserModel> loginUser(@Body UserModel userModel);
}
