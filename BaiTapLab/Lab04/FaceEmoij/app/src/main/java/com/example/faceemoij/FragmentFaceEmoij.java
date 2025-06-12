package com.example.faceemoij;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class FragmentFaceEmoij  extends Fragment implements View.OnClickListener {
    private static final int[] ids = {R.id.iv_face1, R.id.iv_face2, R.id.iv_face3, R.id.iv_face4,
            R.id.iv_face5, R.id.iv_face6, R.id.iv_face7, R.id.iv_face8, R.id.iv_face9};

    private static final int[] emojiDrawables = {
            R.drawable.ic_angry,
            R.drawable.ic_baffle,
            R.drawable.ic_beauty,
            R.drawable.ic_boss,
            R.drawable.ic_choler,
            R.drawable.ic_dribble,
            R.drawable.ic_look_down,
            R.drawable.ic_sure,
            R.drawable.ic_tire,
            R.drawable.ic_baffle  // có thể lặp hoặc thêm icon thứ 10
    };

    private Context mContext;
    private Random random;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        random = new Random();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m001_frg_face_emoij, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View v) {
        for (int id : ids) {
            v.findViewById(id).setOnClickListener(this);
        }

        Button btnRandom = v.findViewById(R.id.btnRandom);
        btnRandom.setOnClickListener(view -> {
            int randomIndex = random.nextInt(emojiDrawables.length);
            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = mContext.getResources().getDrawable(emojiDrawables[randomIndex]);
            showToast(drawable);
        });
    }

    @Override
    public void onClick(View v) {
        ImageView ivFace = (ImageView) v;
        showToast(ivFace.getDrawable());
    }

    private void showToast(Drawable drawable) {
        Toast toast = new Toast(mContext);
        ImageView ivEmoij = new ImageView(mContext);
        ivEmoij.setImageDrawable(drawable);
        toast.setView(ivEmoij);
        toast.setDuration(Toast.LENGTH_SHORT); // ~1.5 seconds
        toast.show();

        // Nếu muốn chính xác 1.5 giây:
        new Handler().postDelayed(toast::cancel, 1500);
    }
}
