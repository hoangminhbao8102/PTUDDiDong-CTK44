package com.example.loginapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class M001LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        TextView tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(view -> showCustomToast());
    }

    @SuppressLint("SetTextI18n")
    private void showCustomToast() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText("Bạn đã đăng nhập thành công với email: " + email + " và mật khẩu: " + password);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
