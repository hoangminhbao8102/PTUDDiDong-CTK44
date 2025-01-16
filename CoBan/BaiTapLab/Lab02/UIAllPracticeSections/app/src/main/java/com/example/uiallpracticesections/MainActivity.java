package com.example.uiallpracticesections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button to navigate to Splash Screen
        Button btnSplash = findViewById(R.id.btn_splash);
        btnSplash.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, M000ActSplash.class);
            startActivity(intent);
        });

        // Button to navigate to Profile Screen
        Button btnProfile = findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, M001ActProfile.class);
            startActivity(intent);
        });

        // Button to navigate to Loading Screen
        Button btnLoading = findViewById(R.id.btn_loading);
        btnLoading.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, M002ActLoading.class);
            startActivity(intent);
        });
    }
}