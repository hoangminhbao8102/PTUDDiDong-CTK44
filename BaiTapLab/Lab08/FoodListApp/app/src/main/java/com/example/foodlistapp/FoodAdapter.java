package com.example.foodlistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food> {
    private final Context context;
    private final List<Food> foodList;

    public FoodAdapter(Context context, List<Food> foodList) {
        super(context, 0, foodList);
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
        }

        Food food = foodList.get(position);

        ImageView imgFood = row.findViewById(R.id.imgFood);
        TextView txtTitle = row.findViewById(R.id.txtTitle);
        TextView txtDescription = row.findViewById(R.id.txtDescription);

        imgFood.setImageResource(food.getImageResId());
        txtTitle.setText(food.getTitle());
        txtDescription.setText(food.getDescription());

        return row;
    }
}
