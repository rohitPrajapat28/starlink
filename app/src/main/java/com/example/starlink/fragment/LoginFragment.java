package com.example.starlink.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.starlink.activity.MainActivity;
import com.example.starlink.base.BaseFragment;
import com.example.starlink.R;
import com.example.starlink.databinding.FragmentLoginBinding;
import com.example.starlink.utils.ValidationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends BaseFragment {
    FragmentLoginBinding binding;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
            mAuth = FirebaseAuth.getInstance();
            binding.reegisterTv.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(R.id.registerFragment);

            });

            binding.loginBtn.setOnClickListener(v -> {
                if (checkValidation()) {
                    logIn();
                }
            });


        }
        return binding.getRoot();

    }

    private void logIn() {
        mAuth.signInWithEmailAndPassword(binding.emailEdt.getText().toString(), binding.passwordEdt.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    showToast( "Log in successful");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                } else {
                    showToast( "Log in Error");

                }
            }
        });

    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.emailEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), "enter e mail");
            return false;
        }
        if (!ValidationUtils.validateEmail(binding.emailEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), "enter valid email");
            return false;
        }
        if (TextUtils.isEmpty(binding.passwordEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), "please enerpassword");
            return false;
        }
        if (binding.passwordEdt.getText().toString().trim().length() < 8) {
            showSnackBar(binding.getRoot(), "must be 6 chr");
            return false;
        }
        return true;
    }

}