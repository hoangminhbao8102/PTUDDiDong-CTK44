package com.example.colorpicker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View viewRGB, viewCMY;

    private int red = 0, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekRed = findViewById(R.id.seekRed);
        SeekBar seekGreen = findViewById(R.id.seekGreen);
        SeekBar seekBlue = findViewById(R.id.seekBlue);

        viewRGB = findViewById(R.id.viewRGB);
        viewCMY = findViewById(R.id.viewCMY);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar.getId() == R.id.seekRed) red = i;
                if (seekBar.getId() == R.id.seekGreen) green = i;
                if (seekBar.getId() == R.id.seekBlue) blue = i;
                updateColors();
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        seekRed.setOnSeekBarChangeListener(listener);
        seekGreen.setOnSeekBarChangeListener(listener);
        seekBlue.setOnSeekBarChangeListener(listener);
    }

    private void updateColors() {
        // RGB color
        int colorRGB = Color.rgb(red, green, blue);
        viewRGB.setBackgroundColor(colorRGB);

        // CMY = 255 - RGB
        int c = 255 - red;
        int m = 255 - green;
        int y = 255 - blue;
        int colorCMY = Color.rgb(c, m, y);
        viewCMY.setBackgroundColor(colorCMY);
    }
}