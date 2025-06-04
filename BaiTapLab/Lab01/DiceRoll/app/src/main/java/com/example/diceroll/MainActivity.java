package com.example.diceroll;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView diceImage;
    private final int[] diceImages = {
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
    };

    private void rollDice() {
        var random = new Random();
        int index = random.nextInt(6); // Tạo số ngẫu nhiên từ 0 đến 5
        diceImage.setImageResource(diceImages[index]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        diceImage = findViewById(R.id.diceImage);
        View rootLayout = findViewById(R.id.rootLayout);

        // Bắt sự kiện nhấn vào toàn bộ màn hình
        rootLayout.setOnClickListener(view -> rollDice());
    }
}