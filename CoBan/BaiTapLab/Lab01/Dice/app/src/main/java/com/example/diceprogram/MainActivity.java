package com.example.diceprogram;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final Random random = new Random(); // Đối tượng để tạo số ngẫu nhiên

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EdgeToEdge (giữ nguyên phần này)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lấy các view từ layout
        ImageView diceImage = findViewById(R.id.diceImage);
        Button rollButton = findViewById(R.id.rollButton);

        // Xử lý khi nhấn nút
        rollButton.setOnClickListener(v -> {
            int diceResult = random.nextInt(6) + 1; // Kết quả từ 1 đến 6
            int drawableResource = getDiceDrawable(diceResult);
            diceImage.setImageResource(drawableResource); // Đặt hình ảnh xúc xắc
        });
    }

    // Hàm lấy resource hình ảnh xúc xắc
    private int getDiceDrawable(int diceResult) {
        switch (diceResult) {
            case 1:
                return R.drawable.dice_1;
            case 2:
                return R.drawable.dice_2;
            case 3:
                return R.drawable.dice_3;
            case 4:
                return R.drawable.dice_4;
            case 5:
                return R.drawable.dice_5;
            case 6:
                return R.drawable.dice_6;
            default:
                return R.drawable.dice_1;
        }
    }
}