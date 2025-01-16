package com.example.uifunction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_splash);

        // Tham chiếu đến LinearLayout và ImageView
        LinearLayout rootLayout = findViewById(R.id.root_layout);
        ImageView imageView = findViewById(R.id.imageView);

        // Danh sách màu từ colors.xml
        int[] colors = {
                getResources().getColor(R.color.purple_200),
                getResources().getColor(R.color.purple_500),
                getResources().getColor(R.color.purple_700),
                getResources().getColor(R.color.teal_200),
                getResources().getColor(R.color.teal_700)
        };

        // Danh sách drawable icon
        int[] icons = {
                R.drawable.ic_penguin,
                R.drawable.ic_cat,
                R.drawable.ic_dog,
                R.drawable.ic_bird
        };

        // Random màu nền và icon
        Random random = new Random();
        int randomColor = colors[random.nextInt(colors.length)];
        int randomIcon = icons[random.nextInt(icons.length)];

        // Set màu nền và icon
        rootLayout.setBackgroundColor(randomColor);
        imageView.setImageResource(randomIcon);
    }
}