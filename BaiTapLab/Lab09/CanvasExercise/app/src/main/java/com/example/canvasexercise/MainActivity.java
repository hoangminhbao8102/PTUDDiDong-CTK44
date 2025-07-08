package com.example.canvasexercise;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCanvas myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCanvas = findViewById(R.id.myCanvas);
        Button btnDraw = findViewById(R.id.btnDraw);

        btnDraw.setOnClickListener(view -> {
            // Bạn có thể random giá trị hoặc nhập từ EditText
            int[] barVals = {20 + (int)(Math.random()*80), 20 + (int)(Math.random()*80), 20 + (int)(Math.random()*80)};
            int[] pieVals = {30, 50, 20};
            int[] lineVals = {10, 30, 70, 50, 90};

            myCanvas.setBarValues(barVals);
            myCanvas.setPieValues(pieVals);
            myCanvas.setLineValues(lineVals);
        });
    }
}