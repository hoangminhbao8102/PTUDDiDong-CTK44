package com.example.newsapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;
import com.example.newsapp.data.SQLiteHelper;
import com.example.newsapp.models.Article;

public class AddEditArticleActivity extends AppCompatActivity {

    private TextView tvHeader;
    private EditText edtTitle, edtContent;
    private Button btnSave;
    private SQLiteHelper db;

    private boolean isEditMode = false;
    private int articleId = -1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_article);

        db = new SQLiteHelper(this);
        initView();

        // Kiểm tra nếu là chế độ sửa
        if (getIntent() != null && "edit".equals(getIntent().getStringExtra("mode"))) {
            isEditMode = true;
            articleId = getIntent().getIntExtra("id", -1);
            edtTitle.setText(getIntent().getStringExtra("title"));
            edtContent.setText(getIntent().getStringExtra("content"));
            tvHeader.setText("Chỉnh sửa bài viết");
            btnSave.setText("Cập nhật bài viết");
        }

        btnSave.setOnClickListener(v -> saveArticle());
    }

    private void initView() {
        tvHeader = findViewById(R.id.tvHeader);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        btnSave = findViewById(R.id.btnSave);
    }

    private void saveArticle() {
        String title = edtTitle.getText().toString().trim();
        String content = edtContent.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ tiêu đề và nội dung", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditMode) {
            Article article = new Article(articleId, title, content, 0);
            boolean result = db.updateArticle(article);
            if (result) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Lỗi khi cập nhật", Toast.LENGTH_SHORT).show();
            }
        } else {
            Article article = new Article(title, content);
            boolean result = db.addArticle(article);
            if (result) {
                Toast.makeText(this, "Thêm bài viết thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Lỗi khi thêm bài viết", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
