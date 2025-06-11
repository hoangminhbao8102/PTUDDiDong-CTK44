package com.example.truyencuoi;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class M000SplashFrg extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        return inflater.inflate(R.layout.m000_frg_splash, container, false);
    }

    private void initViews() {
        new Handler().postDelayed(this::goToM001Screen, 2000);
    }

    private void goToM001Screen() {
        ((MainActivity) getActivity()).goToM001Screen();
    }
}
