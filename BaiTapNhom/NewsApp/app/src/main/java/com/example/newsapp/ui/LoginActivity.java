package com.example.newsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.example.newsapp.data.SQLiteHelper;
import com.example.newsapp.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView tvRegister, tvForgot;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new SQLiteHelper(this);
        initView();
        initEvent();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgot = findViewById(R.id.tvForgot);
    }

    private void initEvent() {
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = db.checkLogin(username, password);
            if (user != null) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                if (user.getRole().equals("admin")) {
                    startActivity(new Intent(this, AdminMainActivity.class));
                } else {
                    Intent intent = new Intent(this, UserMainActivity.class);
                    intent.putExtra("username", user.getUsername());
                    startActivity(intent);
                }
                finish();
            } else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        tvForgot.setOnClickListener(v -> startActivity(new Intent(this, ForgotPasswordActivity.class)));
    }
}
