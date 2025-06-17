package com.example.animationapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivAnimal;
    private final int[] animationIds = {
            R.anim.alpha,
            R.anim.rotate,
            R.anim.scale,
            R.anim.translate
    };
    private Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews() {
        ivAnimal = findViewById(R.id.iv_animal);
        random = new Random();

        findViewById(R.id.bt_alpha).setOnClickListener(this);
        findViewById(R.id.bt_rotate).setOnClickListener(this);
        findViewById(R.id.bt_scale).setOnClickListener(this);
        findViewById(R.id.bt_trans).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        int animResId = -1;

        if (id == R.id.bt_alpha) {
            animResId = R.anim.alpha;
        } else if (id == R.id.bt_rotate) {
            animResId = R.anim.rotate;
        } else if (id == R.id.bt_scale) {
            animResId = R.anim.scale;
        } else if (id == R.id.bt_trans) {
            animResId = R.anim.translate;
        } else if (id == R.id.bt_random) {
            int index = random.nextInt(animationIds.length);
            animResId = animationIds[index];
        }

        if (animResId != -1) {
            Animation animation = AnimationUtils.loadAnimation(this, animResId);
            ivAnimal.startAnimation(animation);
        }
    }
}