package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.data.SQLiteHelper;
import com.example.newsapp.models.Article;
import com.example.newsapp.ui.ForgotPasswordActivity;
import com.example.newsapp.ui.LoginActivity;
import com.example.newsapp.ui.RegisterActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister, btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();

        // ✅ Thêm đoạn kiểm tra SQLite
        initDatabaseTest();
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

    // ✅ Mở database và log dữ liệu mẫu
    private void initDatabaseTest() {
        SQLiteHelper dbHelper = new SQLiteHelper(this);

        // Log đường dẫn file database
        Log.d("DB_PATH", getDatabasePath("news_app.db").getAbsolutePath());

        // Test: lấy danh sách bài viết và log ra Logcat
        List<Article> articles = dbHelper.getAllArticles();
        for (com.example.newsapp.models.Article a : articles) {
            Log.d("ARTICLE_LOG", a.getTitle() + " - " + a.getViews() + " views");
        }

        // Test: lấy tổng số user
        int totalUsers = dbHelper.getTotalUsers();
        Log.d("STATS", "Total users: " + totalUsers);
    }
}
