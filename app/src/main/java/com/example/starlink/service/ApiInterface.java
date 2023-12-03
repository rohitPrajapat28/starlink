package com.example.starlink.service;

import static com.example.starlink.utils.BaseServiceFCM.CONTENT_TYPE;
import static com.example.starlink.utils.BaseServiceFCM.SERVER_KEY;

import com.example.starlink.models.PushNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Authorization"+ SERVER_KEY,"Content-Type"+ CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotification> sendNotification(@Body PushNotification pushNotification);

}
