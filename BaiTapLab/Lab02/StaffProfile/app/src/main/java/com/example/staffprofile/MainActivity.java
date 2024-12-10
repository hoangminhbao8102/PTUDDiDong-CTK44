package com.example.staffprofile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_profile);

        // Tìm ImageView theo ID và thiết lập sự kiện click
        ImageView icPhone = findViewById(R.id.ic_phone);
        icPhone.setOnClickListener(v -> {
            // Tạo Intent gọi điện
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:(650) 555-1234")); // Số điện thoại từ strings.xml
            startActivity(callIntent);
        });
    }
}