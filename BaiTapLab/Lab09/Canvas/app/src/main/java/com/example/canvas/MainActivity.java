package com.example.canvas;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int[] colors = new int[]{Color.RED, Color.GREEN, Color.GRAY, Color.YELLOW, Color.CYAN, Color.WHITE};
    Random rd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyCanvas canvas = findViewById(R.id.myCanvas);
        Button btnDraw = findViewById(R.id.btnDraw);
        btnDraw.setOnClickListener(view -> {
            // Random chỉ số màu
            int colorIndex = rd.nextInt(colors.length);
            MyCanvas.color = colors[colorIndex];
            canvas.invalidate(); // Vẽ lại
        });
    }
}