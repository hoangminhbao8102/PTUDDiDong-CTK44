package com.example.animalsound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSplash, btnLoading, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSplash = findViewById(R.id.btnSplash);
        btnLoading = findViewById(R.id.btnLoading);
        btnProfile = findViewById(R.id.btnProfile);

        btnSplash.setOnClickListener(v ->
                startActivity(new Intent(this, M000ActSplash.class)));

        btnLoading.setOnClickListener(v ->
                startActivity(new Intent(this, M000ActSplash.class))); // Reuse splash with loading

        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(this, M001ActProfile.class)));
    }
}