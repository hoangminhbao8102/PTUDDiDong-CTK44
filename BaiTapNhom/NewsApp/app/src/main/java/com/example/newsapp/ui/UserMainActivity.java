package com.example.newsapp.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.example.newsapp.adapters.ArticleAdapter;
import com.example.newsapp.data.SQLiteHelper;
import com.example.newsapp.models.Article;

import java.util.List;

public class UserMainActivity extends AppCompatActivity {

    private EditText edtSearch;
    private ListView lvArticles;
    private Button btnChangePassword, btnLogout;

    private SQLiteHelper db;
    private ArticleAdapter adapter;
    private String currentUsername; // lấy từ LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        db = new SQLiteHelper(this);
        currentUsername = getIntent().getStringExtra("username");

        initView();
        loadArticles();
        initEvent();
    }

    private void initView() {
        edtSearch = findViewById(R.id.edtSearch);
        lvArticles = findViewById(R.id.lvArticles);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void loadArticles() {
        List<Article> articleList = db.getAllArticles();
        adapter = new ArticleAdapter(this, articleList);
        lvArticles.setAdapter(adapter);
    }

    private void initEvent() {
        lvArticles.setOnItemClickListener((adapterView, view, position, id) -> {
            Article article = adapter.getItem(position);
            db.increaseViewCount(article.getId()); // tăng lượt xem

            Intent intent = new Intent(this, ArticleDetailActivity.class);
            intent.putExtra("title", article.getTitle());
            intent.putExtra("content", article.getContent());
            startActivity(intent);
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Article> filtered = db.searchArticles(s.toString());
                adapter.setFilter(filtered);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });

        btnChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            intent.putExtra("username", currentUsername);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                .setPositiveButton("Có", (dialog, which) -> {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                })
                .setNegativeButton("Không", null)
                .show());
    }
}
