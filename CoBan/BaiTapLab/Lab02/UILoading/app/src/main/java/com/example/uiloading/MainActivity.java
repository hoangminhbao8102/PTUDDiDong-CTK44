package com.example.uiloading;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_splash);

        // Lấy ProgressBar từ layout
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Tạo hiệu ứng quay
        ObjectAnimator animation = ObjectAnimator.ofFloat(progressBar, "rotation", 0f, 360f);
        animation.setDuration(1000); // Thời gian quay (1 giây)
        animation.setRepeatCount(ObjectAnimator.INFINITE); // Quay liên tục
        animation.start(); // Bắt đầu hoạt ảnh
    }
}