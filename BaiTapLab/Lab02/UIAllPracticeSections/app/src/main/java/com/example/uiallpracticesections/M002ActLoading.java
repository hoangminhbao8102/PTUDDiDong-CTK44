package com.example.uiallpracticesections;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class M002ActLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_act_loading);

        // Lấy ProgressBar từ layout
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Tạo hiệu ứng quay
        ObjectAnimator animation = ObjectAnimator.ofFloat(progressBar, "rotation", 0f, 360f);
        animation.setDuration(1000); // Thời gian quay (1 giây)
        animation.setRepeatCount(ObjectAnimator.INFINITE); // Quay liên tục
        animation.start(); // Bắt đầu hoạt ảnh
    }
}
