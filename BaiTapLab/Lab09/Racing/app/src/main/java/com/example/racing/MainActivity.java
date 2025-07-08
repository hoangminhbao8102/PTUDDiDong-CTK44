package com.example.racing;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RacingView racingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        racingView = findViewById(R.id.racingView);
        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> racingView.startRace());
    }
}