package com.example.mygrocerystore.api;

import com.example.mygrocerystore.models.LoginModel;
import com.example.mygrocerystore.models.PopularModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface ProductApi {
    @GET("api/products")
    Call<List<PopularModel>> getProducts();
}
