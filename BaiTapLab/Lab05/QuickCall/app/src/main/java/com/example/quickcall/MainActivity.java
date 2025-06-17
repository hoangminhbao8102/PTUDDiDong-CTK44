package com.example.quickcall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Animation animScale;
    private Animation animRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnimations();
        initView();
    }
    private void initAnimations() {
        AnimationUtils.loadAnimation(this, R.anim.alpha);
        animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        AnimationUtils.loadAnimation(this, R.anim.translate);
    }
    private void initView() {
        findViewById(R.id.fr_mom).setOnClickListener(this);
        findViewById(R.id.fr_dad).setOnClickListener(this);
        findViewById(R.id.fr_crush).setOnClickListener(this);
        findViewById(R.id.fr_best_friend).setOnClickListener(this);
        findViewById(R.id.iv_dialer).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // Áp dụng hiệu ứng xoay cho tất cả khi được nhấn
        v.startAnimation(animRotate);
        if (v instanceof FrameLayout) {
            processCall((String) v.getTag());
            return;
        }
        // Nếu là icon dialpad thì mở màn hình quay số + hiệu ứng scale
        v.startAnimation(animScale);
        gotoDialPad();
    }
    private void gotoDialPad() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }
    private void processCall(String phone) {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
            Toast.makeText(this, "Hãy thực hiện lại sau khi cấp quyền!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + phone));
        startActivity(intent);
    }
}