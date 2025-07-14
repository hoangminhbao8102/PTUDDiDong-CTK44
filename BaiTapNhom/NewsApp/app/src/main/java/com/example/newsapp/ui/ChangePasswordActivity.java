package com.example.newsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.example.newsapp.data.SQLiteHelper;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText edtOldPassword, edtNewPassword, edtConfirmPassword;
    private Button btnChangePassword;
    private SQLiteHelper db;
    private String currentUsername; // nhận từ UserMainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        db = new SQLiteHelper(this);
        currentUsername = getIntent().getStringExtra("username");

        initView();
        initEvent();
    }

    private void initView() {
        edtOldPassword = findViewById(R.id.edtOldPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
    }

    private void initEvent() {
        btnChangePassword.setOnClickListener(v -> {
            String oldPass = edtOldPassword.getText().toString().trim();
            String newPass = edtNewPassword.getText().toString().trim();
            String confirmPass = edtConfirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(oldPass) || TextUtils.isEmpty(newPass) || TextUtils.isEmpty(confirmPass)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            String currentPass = db.getPassword(currentUsername);
            if (!oldPass.equals(currentPass)) {
                Toast.makeText(this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Mật khẩu mới xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = db.updatePassword(currentUsername, newPass);
            if (success) {
                Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Có lỗi xảy ra khi đổi mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
