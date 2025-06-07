package com.example.animalsound;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class M000ActSplash extends Activity {

    int[] colors = {
            R.color.bg_blue,
            R.color.bg_green,
            R.color.bg_red,
            R.color.bg_purple
    };

    int[] icons = {
            R.drawable.ic_penguin,
            R.drawable.ic_cat,
            R.drawable.ic_dog,
            R.drawable.ic_lion
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_splash);

        LinearLayout layoutRoot = findViewById(R.id.layoutRoot);
        ImageView imgAnimal = findViewById(R.id.imgAnimal);

        Random rand = new Random();

        int randomColor = colors[rand.nextInt(colors.length)];
        int randomIcon = icons[rand.nextInt(icons.length)];

        layoutRoot.setBackgroundResource(randomColor);
        imgAnimal.setImageResource(randomIcon);
    }
}
