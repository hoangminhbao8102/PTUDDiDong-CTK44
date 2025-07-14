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

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtUsername, edtNewPassword, edtConfirmPassword;
    private Button btnResetPassword;
    private TextView tvBackLogin;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        db = new SQLiteHelper(this);
        initView();
        initEvent();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        tvBackLogin = findViewById(R.id.tvBackLogin);
    }

    private void initEvent() {
        btnResetPassword.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String newPassword = edtNewPassword.getText().toString().trim();
            String confirmPassword = edtConfirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!db.isUsernameExists(username)) {
                Toast.makeText(this, "Tên đăng nhập không tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = db.updatePassword(username, newPassword);
            if (success) {
                Toast.makeText(this, "Đặt lại mật khẩu thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Có lỗi xảy ra khi cập nhật mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        tvBackLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
