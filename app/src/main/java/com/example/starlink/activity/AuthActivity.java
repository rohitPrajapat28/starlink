package com.example.starlink.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.starlink.R;
import com.example.starlink.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {
    ActivityAuthBinding binding;
    NavController navController;
    NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(AuthActivity.this,R.layout.activity_auth);
          // navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_auth);
           navController = Navigation.findNavController(this, R.id.nav_host_auth);
        getWindow().setStatusBarColor(getResources().getColor(R.color.F44336));

    }
}
