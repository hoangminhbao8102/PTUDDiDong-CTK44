package com.example.foodgridapp;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;

public class FoodGridAdapter extends BaseAdapter {
    private final Context context;
    private final List<Food> foodList;

    public FoodGridAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView img = row.findViewById(R.id.imgFood);
        TextView txt = row.findViewById(R.id.txtFoodName);

        Food food = foodList.get(position);

        img.setImageResource(food.getImageResId());
        txt.setText(food.getName());

        return row;
    }
}
