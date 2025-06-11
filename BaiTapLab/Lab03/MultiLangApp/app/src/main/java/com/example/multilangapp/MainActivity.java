package com.example.multilangapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnVi, btnEn, btnFr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(getSharedPreferences("lang", MODE_PRIVATE).getString("lang_code", "en"));
        setContentView(R.layout.activity_main);

        btnVi = findViewById(R.id.btnVi);
        btnEn = findViewById(R.id.btnEn);
        btnFr = findViewById(R.id.btnFr);

        btnVi.setOnClickListener(v -> changeLanguage("vi"));
        btnEn.setOnClickListener(v -> changeLanguage("en"));
        btnFr.setOnClickListener(v -> changeLanguage("fr"));
    }

    private void changeLanguage(String langCode) {
        getSharedPreferences("lang", MODE_PRIVATE)
                .edit()
                .putString("lang_code", langCode)
                .apply();
        recreate(); // restart activity to reload UI
    }

    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}