package com.example.funnystorylab10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class M001TopicFrg extends Fragment implements View.OnClickListener {
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m001_frg_topic, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
    private void initViews(View v) {
        LinearLayout lnMain = v.findViewById(R.id.ln_topic);
        lnMain.removeAllViews();
        try {
            String[] listItem = mContext.getAssets().list("photo");
            assert listItem != null;
            for (String fileName : listItem) {
                String name = fileName.substring(0, fileName.indexOf("."));
                @SuppressLint("InflateParams") View vTopic = LayoutInflater.from(mContext).inflate(R.layout.item_topic, null);
                ImageView ivTopic = vTopic.findViewById(R.id.iv_topic);
                TextView tvTopic = vTopic.findViewById(R.id.tv_topic);
                ivTopic.setImageBitmap(BitmapFactory
                        .decodeStream(mContext.getAssets().open("photo/" + fileName)));
                tvTopic.setText(name);
                lnMain.addView(vTopic);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                        vTopic.getLayoutParams();
                params.bottomMargin = 40;
                vTopic.setLayoutParams(params);
                vTopic.setTag(name);
                vTopic.setOnClickListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public void onClick(View v) {
        ((MainActivity) Objects.requireNonNull(getActivity())).gotoM002Screen((String)v.getTag());
    }
}
