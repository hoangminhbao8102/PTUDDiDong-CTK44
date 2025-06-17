package com.example.currencyconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Khai báo biến lưu danh sách đơn vị tiền tệ
    private final String[] units = {
            "USD", "EUR", "GBP", "INR", "AUD", "CAD", "ZAR", "NZD", "JPY", "VND"
    };

    private final double[][] ratio = {
            {1.00000, 0.80518, 0.6407, 63.3318, 1.21828, 1.16236, 11.7129, 1.2931, 118.337, 21385.7},
            {1.24172, 1.00000, 0.79575, 78.6084, 1.51266, 1.44314, 14.5371, 1.60576, 146.927, 26561.8},
            {1.56044, 1.25667, 1.00000, 98.7848, 1.90091, 1.81355, 18.2683, 2.01791, 184.638, 33374.9},
            {0.0158, 0.01272, 0.01012, 1.00000, 0.01924, 0.01836, 0.18493, 0.02043, 1.8691, 337.811},
            {0.82114, 0.66119, 0.5262, 52.086, 1.00000, 0.95416, 9.61148, 1.06158, 97.112, 17567.9},
            {0.86059, 0.69296, 0.55148, 54.5885, 1.04804, 1.00000, 10.0732, 1.11258, 101.777, 18401.7},
            {0.08541, 0.06877, 0.05473, 5.40852, 0.10398, 0.09924, 1.00000, 0.11037, 10.0996, 1825.87},
            {0.77402, 0.62319, 0.49597, 49.0031, 0.94215, 0.89951, 9.06754, 1.00000, 91.5139, 16552.1},
            {0.00846, 0.00681, 0.00542, 0.53547, 0.0103, 0.00983, 0.09908, 0.01093, 1.00000, 180.837},
            {0.00005, 0.00004, 0.00003, 0.00296, 0.00006, 0.00005, 0.00055, 0.00006, 0.00553, 1.00000}
    };

    // Khái báo các đối tượng View
    private EditText txtNumber;
    private Spinner spnUnit;
    private TextView[] lblResult;

    // Hàm đổi đơn vị tiền tệ
    @SuppressLint("DefaultLocale")
    private void changeMoneyUnit() {
        // Lấy vị trí của đơn vị được chọn
        int rowIdx = spnUnit.getSelectedItemPosition();

        if (rowIdx < 0) rowIdx = 0;

        // Lấy giá trị từ ô nhập
        String input = txtNumber.getText().toString();

        if (input.isEmpty()) input = "0";

        // Đổi giá trị nhập sang số thực
        double number = Double.parseDouble(input);
        // Tính giá trị quy đổi ứng với từng loại tiền
        for (int i = 0; i < lblResult.length; i++) {
            double temp = number * ratio[rowIdx][i];

            // Hiển thị kết quả lên TextView tương ứng
            lblResult[i].setText(String.format("%.5f", temp));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kết nối các đối tượng View
        txtNumber = findViewById(R.id.txtNumber);
        spnUnit = findViewById(R.id.spnUnit);
        lblResult = new TextView[]{
                findViewById(R.id.lblUsd),
                findViewById(R.id.lblEur),
                findViewById(R.id.lblGbp),
                findViewById(R.id.lblInr),
                findViewById(R.id.lblAud),
                findViewById(R.id.lblCad),
                findViewById(R.id.lblZar),
                findViewById(R.id.lblNzd),
                findViewById(R.id.lblJpy),
                findViewById(R.id.lblVnd)
        };

        // Khởi tạo dối tượng trung chuyển
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, units);

        // Thiết lập cách hiển thị của Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // Gắn adapter vào Spinner
        spnUnit.setAdapter(adapter);

        // Thiết lập hàm xử lý sự kiện thay item được chọn trong Spinner
        spnUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                changeMoneyUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Thiết lập hàm xử lý sự kiện thay đổi nội dung ô nhập
        txtNumber.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeMoneyUnit();
            }
            public void afterTextChanged(Editable s) { }
        });
    }
}
