package com.example.lengthconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtInput;
    private Spinner spinnerUnit;
    private ListView listView;
    private final String[] units = {
            "Hải lý", "Dặm", "Kilometer", "Lý", "Met", "Yard", "Foot", "Inches"
    };

    private final double[][] ratio = {
            {1, 1.5077945, 1.8520000, 20.2537183, 1852.0000, 2025.37183, 6076.11549, 72913.38583},
            {0.06897624, 1, 1.6093440, 17.6000000, 1609.3440, 1760.00000, 5280.00000, 63360.00000},
            {0.53995680, 0.62137119, 1, 10.9361330, 1000.0000, 1093.61330, 3280.83990, 39370.07874},
            {0.04937365, 0.05681818, 0.0914400, 1, 91.4400, 100.00000, 300.00000, 3600.00000},
            {0.00053996, 0.00062137, 0.0010000, 0.0109361, 1.0000, 1.09361, 3.28084, 39.37008},
            {0.00049374, 0.00056818, 0.0009144, 0.0100000, 0.9144, 1.00000, 3, 36},
            {0.00016458, 0.00018939, 0.0003048, 0.0033333, 0.3048, 0.33333, 1, 12},
            {0.00001371, 0.00001578, 0.0000254, 0.0002778, 0.0254, 0.02778, 0.08333, 1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInput = findViewById(R.id.edtInput);
        spinnerUnit = findViewById(R.id.spinnerUnit);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);

        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertAndShow();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        edtInput.setOnEditorActionListener((v, actionId, event) -> {
            convertAndShow();
            return false;
        });
    }

    @SuppressLint("DefaultLocale")
    private void convertAndShow() {
        String inputStr = edtInput.getText().toString();
        if (inputStr.isEmpty()) return;

        double input = Double.parseDouble(inputStr);
        int fromUnitIndex = spinnerUnit.getSelectedItemPosition();

        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < units.length; i++) {
            double converted = input * ratio[fromUnitIndex][i];
            results.add(String.format("%.4f\t\t%s", converted, units[i]));
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, results);
        listView.setAdapter(listAdapter);
    }
}
