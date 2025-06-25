package com.example.foodlistapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Food> foodList;
    FoodAdapter adapter;
    int selectedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewFood);
        foodList = new ArrayList<>();

        foodList.add(new Food("Phở", "Phở bò truyền thống", R.drawable.pho));
        foodList.add(new Food("Bánh mì", "Bánh mì kẹp thịt", R.drawable.banhmi));
        foodList.add(new Food("Cơm tấm", "Cơm tấm sườn bì chả", R.drawable.comtam));

        adapter = new FoodAdapter(this, foodList);
        listView.setAdapter(adapter);

        // Click item
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Food selected = foodList.get(i);
            Toast.makeText(this, "Bạn chọn: " + selected.getTitle(), Toast.LENGTH_SHORT).show();
        });

        // Long click context menu
        registerForContextMenu(listView);
    }

    // Tạo context menu (Sửa, Xoá)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, v, info);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) info;
        selectedItem = acmi.position;
    }

    // Xử lý chọn context menu
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (selectedItem == -1) return super.onContextItemSelected(item);

        int itemId = item.getItemId();

        if (itemId == R.id.menu_edit) {
            showFoodDialog("Sửa món ăn", selectedItem);
            return true;
        } else if (itemId == R.id.menu_delete) {
            foodList.remove(selectedItem);
            adapter.notifyDataSetChanged();
            selectedItem = -1;
            return true;
        }

        return super.onContextItemSelected(item);
    }

    // Thêm menu trên Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    // Xử lý chọn "Thêm món ăn"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            showFoodDialog("Thêm món ăn", -1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Hiển thị Dialog để thêm/sửa
    private void showFoodDialog(String title, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        View view = getLayoutInflater().inflate(R.layout.dialog_food, null);
        EditText edtTitle = view.findViewById(R.id.edtTitle);
        EditText edtDesc = view.findViewById(R.id.edtDesc);

        if (index >= 0) {
            edtTitle.setText(foodList.get(index).getTitle());
            edtDesc.setText(foodList.get(index).getDescription());
        }

        builder.setView(view);
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String titleText = edtTitle.getText().toString();
            String descText = edtDesc.getText().toString();

            if (index >= 0) {
                Food f = foodList.get(index);
                foodList.set(index, new Food(titleText, descText, f.getImageResId()));
            } else {
                foodList.add(new Food(titleText, descText, R.drawable.pho)); // mặc định ảnh phở
            }

            adapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Huỷ", null);
        builder.show();
    }
}