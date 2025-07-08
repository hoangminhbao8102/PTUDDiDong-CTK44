package com.example.zodiacapplab11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class M001MenuActivity extends AppCompatActivity {

    private ImageView imgSelected;
    private TextView tvName, tvContent;
    private int currentImageRes;
    private String currentName, currentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_menu);

        imgSelected = findViewById(R.id.imgSelectedZodiac);
        tvName = findViewById(R.id.tvZodiacName);
        tvContent = findViewById(R.id.tvZodiacContent);
        Button btnMore = findViewById(R.id.btnMore);

        setupZodiac(findViewById(R.id.imgAries), R.drawable.aries);
        // Gọi tiếp setupZodiac cho các cung khác...

        btnMore.setOnClickListener(v -> {
            Intent intent = new Intent(M001MenuActivity.this, M002DetailActivity.class);
            intent.putExtra("name", currentName);
            intent.putExtra("imageRes", currentImageRes);
            intent.putExtra("content", currentContent);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void setupZodiac(ImageView img, int imgRes) {
        img.setOnClickListener(v -> {
            imgSelected.setImageResource(imgRes);
            tvName.setText("Bạch Dương");
            tvContent.setText("Nội dung Bạch Dương...");
            currentName = "Bạch Dương";
            currentImageRes = imgRes;
            currentContent = "Nội dung Bạch Dương...";
        });
    }
}
