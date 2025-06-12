package com.example.englishlearning;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class TopicVocabularyActivity extends AppCompatActivity {
    private static final Map<String, String[]> VOCAB_MAP = new HashMap<>();

    static {
        VOCAB_MAP.put("Essentials", new String[]{"Hello", "Thank you", "Please", "Sorry"});
        VOCAB_MAP.put("While Traveling", new String[]{"Ticket", "Flight", "Luggage"});
        VOCAB_MAP.put("At the Restaurant", new String[]{"Menu", "Waiter", "Bill"});
        // Thêm các chủ đề và từ vựng khác nếu cần
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_vocabulary);

        String topic = getIntent().getStringExtra("topic");
        String[] vocabList = VOCAB_MAP.getOrDefault(topic, new String[]{"No data"});

        TextView tvTopicTitle = findViewById(R.id.tvTopicTitle);
        tvTopicTitle.setText("Topic: " + topic);

        ListView lvVocab = findViewById(R.id.lvVocabularies);
        assert vocabList != null;
        lvVocab.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vocabList));
    }
}
