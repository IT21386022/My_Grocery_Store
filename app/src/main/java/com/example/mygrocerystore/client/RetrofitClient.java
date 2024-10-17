package com.example.mygrocerystore.client;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitClient {

    private static Retrofit retrofit;

    private static final String BASE_URL = "http://10.0.2.2:5050";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()

                    .connectTimeout(60, TimeUnit.SECONDS)

                    .readTimeout(60, TimeUnit.SECONDS)

                    .writeTimeout(60, TimeUnit.SECONDS)

                    .build();

            retrofit = new Retrofit.Builder()

                    .baseUrl(BASE_URL)

                    .client(okHttpClient)

                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

        }

        return retrofit;

    }

}

