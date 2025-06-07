package com.example.animalsound;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class M001ActProfile  extends AppCompatActivity {

    private static final String PHONE_NUMBER = "(650) 555-1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_act_profile);

        ImageView imgPhone1 = findViewById(R.id.imgPhone1);

        imgPhone1.setOnClickListener(view -> {
            String cleanedNumber = PHONE_NUMBER.replaceAll("[^\\d+]", ""); // Remove spaces, parentheses
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + cleanedNumber));
            startActivity(callIntent);
        });
    }
}
