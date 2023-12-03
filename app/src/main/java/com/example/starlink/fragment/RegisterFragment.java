package com.example.starlink.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.starlink.base.BaseFragment;
import com.example.starlink.R;
import com.example.starlink.utils.ValidationUtils;
import com.example.starlink.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends BaseFragment {

    FragmentRegisterBinding binding;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        mAuth = FirebaseAuth.getInstance();
        binding.loginTv.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.loginFragment);
        });
        binding.registerBtn.setOnClickListener(v -> {
            if (checkValidation()) {
                createUser();
            }
        });
        return binding.getRoot();
    }

    private void createUser() {
        mAuth.createUserWithEmailAndPassword
                (binding.registerEmailEdt.getText().toString(), binding.registerpasswordEdt.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    showToast("Registration successful");
                    Navigation.findNavController(getView()).navigate(R.id.loginFragment);
                }else {
                    showToast("Registration Error " + task.getException().getMessage());
                }
            }
        });
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.registerEmailEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), "enter email");
            return false;
        }
        if (!ValidationUtils.validateEmail(binding.registerEmailEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), "enter valid email");
            return false;
        }
        if (TextUtils.isEmpty(binding.registerpasswordEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), "please set a password");
            return false;
        }
        if (binding.registerpasswordEdt.getText().toString().trim().length() < 8) {
            showSnackBar(binding.getRoot(), "must be 6 chr");
            return false;
        }
        return true;
    }
}