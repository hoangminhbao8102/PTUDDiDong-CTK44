package com.example.zodiacapplab11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class M002DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_act_detail);

        TextView tvName = findViewById(R.id.tvNameDetail);
        TextView tvContent = findViewById(R.id.tvContentDetail);
        ImageView imgZodiac = findViewById(R.id.imgZodiacDetail);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvContent.setText(intent.getStringExtra("content"));
        imgZodiac.setImageResource(intent.getIntExtra("imageRes", R.drawable.aries));

        btnBack.setOnClickListener(v -> finish());
    }
}
