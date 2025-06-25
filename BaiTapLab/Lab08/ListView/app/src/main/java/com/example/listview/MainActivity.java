package com.example.listview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] titles = new String[]{"Android", "iOS", "Window Phone"};
    String[] contents = new String[]{"Đây là hệ điều hành Android",
                                    "Đây là hệ điều hành iOS",
                                    "Đây là hệ điều hành Window Phone"};
    int[] imgs = new int[]{ R.drawable.android,
                            R.drawable.ios,
                            R.drawable.windows_mobile};
    ListView lvMain;
    TextView txtDisplay;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMain = findViewById(R.id.lvMain);
        txtDisplay = findViewById(R.id.txtDisplay);
        // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        ArrayList<Product> list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(new Product(imgs[i], titles[i], contents[i]));
        }
        MyListViewAdapter adapter = new MyListViewAdapter(list);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener((parent, view, position, id) -> txtDisplay.setText("Bạn chọn: " + titles[position]));
    }
}