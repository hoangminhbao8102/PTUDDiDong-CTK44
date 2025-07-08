package com.example.funnystorylab10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class M002StoryFrg extends Fragment {
    private Context mContext;
    private String topicName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m002_frg_story, container, false);
        initViews(rootView);
        return rootView;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
    private void initViews(View v){
        v.findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        v.findViewById(R.id.iv_back).setOnClickListener(v1 -> backToM001Screen());
        ((TextView) v.findViewById(R.id.tv_name)).setText(topicName);
        RecyclerView rv = v.findViewById(R.id.rv_story);
        ArrayList<StoryEntity> listStory = readStory();
        StoryAdapter adapter = new StoryAdapter(listStory,mContext);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
    }
    private ArrayList<StoryEntity> readStory() {
        ArrayList<StoryEntity> listStory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(mContext.getAssets().open("story/" + topicName + ".txt"), StandardCharsets.UTF_8))) {

            String title;
            while ((title = reader.readLine()) != null) {
                StringBuilder content = new StringBuilder();
                String mLine;

                while ((mLine = reader.readLine()) != null) {
                    content.append(mLine).append("\n");
                    if (mLine.contains("','0');")) {
                        break;
                    }
                }

                String cleanedContent = content.toString().replace("','0');", "");
                StoryEntity storyEntity = new StoryEntity(topicName, title, cleanedContent);
                listStory.add(storyEntity);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listStory;
    }
    public void setTopicName(String topicName){
        this.topicName = topicName;
    }
    @SuppressLint("UseRequireInsteadOfGet")
    private void backToM001Screen() {
        ((MainActivity) Objects.requireNonNull(getActivity())).backToM001Screen();
    }

}
