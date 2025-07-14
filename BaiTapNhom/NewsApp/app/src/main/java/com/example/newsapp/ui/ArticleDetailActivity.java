package com.example.newsapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;

public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvContent = findViewById(R.id.tvContent);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        tvTitle.setText(title);
        tvContent.setText(content);
    }
}
