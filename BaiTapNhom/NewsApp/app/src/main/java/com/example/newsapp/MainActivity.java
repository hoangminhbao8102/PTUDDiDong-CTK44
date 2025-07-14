package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.ui.ForgotPasswordActivity;
import com.example.newsapp.ui.LoginActivity;
import com.example.newsapp.ui.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister, btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
    }

    private void initEvent() {
        btnLogin.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        btnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));

        btnForgotPassword.setOnClickListener(v -> startActivity(new Intent(this, ForgotPasswordActivity.class)));
    }
}
