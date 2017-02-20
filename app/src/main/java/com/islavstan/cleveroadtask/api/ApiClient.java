package com.islavstan.cleveroadtask.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiClient {
    public static final String BASE_URL = "https://www.googleapis.com/customsearch/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            Gson result = new GsonBuilder().create();
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(result))
                    .baseUrl(BASE_URL).build();
        }
        return retrofit;
    }
}
