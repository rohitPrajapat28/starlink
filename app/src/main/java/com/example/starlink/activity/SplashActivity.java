package com.example.starlink.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.starlink.R;
import com.example.starlink.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);
        mauth = FirebaseAuth.getInstance();
        getWindow().setStatusBarColor(getResources().getColor(R.color.F44336));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                FirebaseUser user = mauth.getCurrentUser();
                if (user == null){
                    startActivity(new Intent(SplashActivity.this,AuthActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
                finish();


            }
        },3000);

    }
}

