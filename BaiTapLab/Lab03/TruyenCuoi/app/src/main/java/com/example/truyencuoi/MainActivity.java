package com.example.truyencuoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg());
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }

    public void goToM001Screen() {

    }

    public void goToM002Screen(String topicName) {

    }

    public void backToM001Screen() {
        goToM001Screen();
    }

    public void goToM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {

    }
}