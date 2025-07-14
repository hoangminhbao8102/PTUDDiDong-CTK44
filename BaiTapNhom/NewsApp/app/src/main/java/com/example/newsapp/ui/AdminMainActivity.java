package com.example.newsapp.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.example.newsapp.adapters.ArticleAdapter;
import com.example.newsapp.data.SQLiteHelper;
import com.example.newsapp.models.Article;

import java.util.List;

public class AdminMainActivity extends AppCompatActivity {

    private TextView tvTotalArticles, tvTotalViews, tvTotalUsers;
    private ListView lvArticles;
    private Button btnAddArticle, btnLogout;

    private SQLiteHelper db;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        db = new SQLiteHelper(this);
        initView();
        loadStats();
        loadArticles();
        initEvent();
    }

    private void initView() {
        tvTotalArticles = findViewById(R.id.tvTotalArticles);
        tvTotalViews = findViewById(R.id.tvTotalViews);
        tvTotalUsers = findViewById(R.id.tvTotalUsers);
        lvArticles = findViewById(R.id.lvArticles);
        btnAddArticle = findViewById(R.id.btnAddArticle);
        btnLogout = findViewById(R.id.btnLogout);
    }

    @SuppressLint("SetTextI18n")
    private void loadStats() {
        tvTotalArticles.setText("Tổng số bài viết: " + db.getTotalArticles());
        tvTotalViews.setText("Tổng lượt xem: " + db.getTotalViews());
        tvTotalUsers.setText("Tổng số người dùng: " + db.getTotalUsers());
    }

    private void loadArticles() {
        List<Article> articleList = db.getAllArticles();
        adapter = new ArticleAdapter(this, articleList);
        lvArticles.setAdapter(adapter);
    }

    private void initEvent() {
        btnAddArticle.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditArticleActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn có muốn đăng xuất không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                })
                .setNegativeButton("Không", null)
                .show());

        lvArticles.setOnItemClickListener((adapterView, view, position, id) -> {
            Article article = adapter.getItem(position);

            String[] options = {"Sửa bài viết", "Xóa bài viết"};
            new AlertDialog.Builder(this)
                    .setTitle("Chọn thao tác")
                    .setItems(options, (dialog, which) -> {
                        if (which == 0) {
                            Intent intent = new Intent(this, AddEditArticleActivity.class);
                            intent.putExtra("mode", "edit");
                            intent.putExtra("id", article.getId());
                            intent.putExtra("title", article.getTitle());
                            intent.putExtra("content", article.getContent());
                            startActivity(intent);
                        } else {
                            confirmDelete(article);
                        }
                    })
                    .show();
        });
    }

    private void confirmDelete(Article article) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa bài viết này?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    db.deleteArticle(article.getId());
                    loadArticles();
                    loadStats();
                    Toast.makeText(this, "Đã xóa bài viết", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadArticles();
        loadStats();
    }
}
