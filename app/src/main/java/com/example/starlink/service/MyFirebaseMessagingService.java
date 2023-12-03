package com.example.starlink.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class MyFirebaseMessagingService extends FirebaseMessagingService {private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    public final String channelId="starlink";

    @Override
    public void onNewToken(@NotNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            if (remoteMessage.getData().size() > 0) {
                Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
                try {
                   // handleDataMessage(remoteMessage.getData(),remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),getApplicationContext());
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
            }
            else if(remoteMessage.toIntent().getExtras()!=null){
                try {
              //      handleBundleMessage(remoteMessage.toIntent().getExtras(),remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),getApplicationContext());
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
            }
            else {
             //   showNotification(getApplicationContext(),remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

            }

        }
        else {
            if(remoteMessage.toIntent().getExtras()!=null){
                try {
              //      handleBundleMessage(remoteMessage.toIntent().getExtras(),remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),getApplicationContext());
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }
            }
            else {
              // showNotification(getApplicationContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            }
        }

    }

}
