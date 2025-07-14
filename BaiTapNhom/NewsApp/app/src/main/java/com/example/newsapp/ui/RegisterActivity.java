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

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtConfirmPassword;
    private Button btnRegister;
    private TextView tvBackLogin;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new SQLiteHelper(this);
        initView();
        initEvent();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvBackLogin = findViewById(R.id.tvBackLogin);
    }

    private void initEvent() {
        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirm = edtConfirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(username, password, "user");
            boolean success = db.registerUser(user);

            if (success) {
                Toast.makeText(this, "Đăng ký thành công! Mời đăng nhập", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        tvBackLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
