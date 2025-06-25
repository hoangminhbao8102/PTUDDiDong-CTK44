package com.example.foodgridapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Food> foodList;
    FoodGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridViewFood);
        foodList = new ArrayList<>();

        foodList.add(new Food("Phở", R.drawable.pho));
        foodList.add(new Food("Bánh mì", R.drawable.banhmi));
        foodList.add(new Food("Cơm tấm", R.drawable.comtam));
        foodList.add(new Food("Chả giò", R.drawable.pho)); // dùng lại ảnh
        foodList.add(new Food("Bún bò", R.drawable.banhmi));
        foodList.add(new Food("Hủ tiếu", R.drawable.comtam));

        adapter = new FoodGridAdapter(this, foodList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(this, "Bạn chọn: " + foodList.get(position).getName(), Toast.LENGTH_SHORT).show());
    }
}