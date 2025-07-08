package com.example.zodiacapplab11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, M001MenuActivity.class);
            startActivity(intent);
        });
    }
}