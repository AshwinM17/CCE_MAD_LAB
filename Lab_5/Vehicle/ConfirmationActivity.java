package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView vehicleTypeTextView, vehicleNumberTextView, rcNumberTextView;
    private Button confirmButton, editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Initialize UI elements
        vehicleTypeTextView = findViewById(R.id.vehicleTypeTextView);
        vehicleNumberTextView = findViewById(R.id.vehicleNumberTextView);
        rcNumberTextView = findViewById(R.id.rcNumberTextView);
        confirmButton = findViewById(R.id.confirmButton);
        editButton = findViewById(R.id.editButton);

        // Retrieve details from intent
        Intent intent = getIntent();
        String vehicleType = intent.getStringExtra("vehicleType");
        String vehicleNumber = intent.getStringExtra("vehicleNumber");
        String rcNumber = intent.getStringExtra("rcNumber");

        // Display the details
        vehicleTypeTextView.setText("Vehicle Type: " + vehicleType);
        vehicleNumberTextView.setText("Vehicle Number: " + vehicleNumber);
        rcNumberTextView.setText("RC Number: " + rcNumber);

        // Set the Confirm button listener
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String serialNumber = generateSerialNumber();
                Toast.makeText(ConfirmationActivity.this, "Parking Allotted! Serial Number: " + serialNumber, Toast.LENGTH_LONG).show();
                // Optionally, finish the activity or navigate to another screen here
            }
        });

        // Set the Edit button listener to go back to MainActivity
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Simply finish this activity so the user can edit the details
                finish();
            }
        });
    }

    private String generateSerialNumber() {
        return "PN" + System.currentTimeMillis(); // Unique serial number based on current time
    }
}