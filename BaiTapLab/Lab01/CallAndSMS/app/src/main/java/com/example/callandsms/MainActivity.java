package com.example.callandsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtPhoneNumber, edtMessage;
    Button btnCall, btnSendSms;

    private void makeCall() {
        String phone = edtPhoneNumber.getText().toString();
        if (!phone.isEmpty()) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            } else {
                Toast.makeText(this, "Chưa cấp quyền gọi điện", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSms() {
        String phone = edtPhoneNumber.getText().toString();
        String message = edtMessage.getText().toString();
        if (!phone.isEmpty() && !message.isEmpty()) {
            try {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phone, null, message, null, null);
                Toast.makeText(this, "Đã gửi tin nhắn", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Gửi thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static final int REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtMessage = findViewById(R.id.edtMessage);
        btnCall = findViewById(R.id.btnCall);
        btnSendSms = findViewById(R.id.btnSendSms);

        btnCall.setOnClickListener(v -> makeCall());
        btnSendSms.setOnClickListener(v -> sendSms());

        // Xin quyền nếu chưa có
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS}, REQUEST_CODE);
        }
    }
}