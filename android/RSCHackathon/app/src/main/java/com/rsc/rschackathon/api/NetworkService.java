package com.rsc.rschackathon.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static String baseUrl = "http://rsc.rinkovec.com";

    private RetrofitAPI retrofitAPI;

    public NetworkService() {
        this(baseUrl);
    }

    public NetworkService(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public RetrofitAPI getAPI() {
        return retrofitAPI;
    }

}
