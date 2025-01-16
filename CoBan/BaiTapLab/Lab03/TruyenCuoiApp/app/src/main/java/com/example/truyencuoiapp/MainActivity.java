package com.example.truyencuoiapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String topicName;
    private boolean isRotationEnabled = true; // Biến kiểm soát trạng thái xoay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg());
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }

    // Bật/Tắt tính năng xoay màn hình
    public void setScreenOrientation(boolean enableRotation) {
        isRotationEnabled = enableRotation;
        if (enableRotation) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    // Ví dụ gọi hàm khi nhấn nút
    public void toggleRotation(View view) {
        if (isRotationEnabled) {
            setScreenOrientation(false); // Tắt xoay màn hình
            Toast.makeText(this, "Rotation Disabled", Toast.LENGTH_SHORT).show();
        } else {
            setScreenOrientation(true); // Bật xoay màn hình
            Toast.makeText(this, "Rotation Enabled", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoM001Screen() {

    }

    public void gotoM002Screen(String topicName) {

    }

    public void backToM001Screen() {
        gotoM001Screen();
    }

    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {

    }
}