package com.example.starlink.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.starlink.R;
import com.example.starlink.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    FirebaseAuth mAuth;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.F44336));
        if(binding == null)
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
      timer();
    }

    private  void timer(){
        countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
               Toast.makeText(MainActivity.this,":" +System.currentTimeMillis(),Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                Toast.makeText(MainActivity.this, "Time Out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
                finish();

            }
        }.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            countDownTimer.cancel();
           timer();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}