package com.example.truyencuoi;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // sửa layout này nếu bạn đã đặt tên khác

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchOrientation = findViewById(R.id.switch_orientation);

        // Xử lý chuyển đổi chế độ xoay
        switchOrientation.setOnCheckedChangeListener((buttonView, isChecked) -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED));

        showFrg(new M000SplashFrg());
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }

    public void goToM001Screen() {

    }

}