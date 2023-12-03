package com.example.starlink.utils;

import com.example.starlink.service.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    public static Retrofit retrofit=null;
    public static ApiInterface  getClients(){
        if (retrofit == null){
            retrofit= new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
