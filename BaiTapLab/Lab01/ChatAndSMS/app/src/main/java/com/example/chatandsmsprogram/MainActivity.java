package com.example.chatandsmsprogram;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;
    private static final int REQUEST_SMS_PERMISSION = 2;

    private EditText phoneNumber, message;
    private Button callButton, smsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.phoneNumber);
        message = findViewById(R.id.message);
        callButton = findViewById(R.id.callButton);
        smsButton = findViewById(R.id.smsButton);

        callButton.setOnClickListener(v -> makeCall());
        smsButton.setOnClickListener(v -> sendSms());
    }

    private void makeCall() {
        String number = phoneNumber.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            startActivity(callIntent);
        }
    }

    private void sendSms() {
        String number = phoneNumber.getText().toString();
        String msg = message.getText().toString();
        if (number.isEmpty() || msg.isEmpty()) {
            Toast.makeText(this, "Enter phone number and message", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);
            Toast.makeText(this, "SMS sent", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall();
            } else {
                Toast.makeText(this, "Call permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSms();
            } else {
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}