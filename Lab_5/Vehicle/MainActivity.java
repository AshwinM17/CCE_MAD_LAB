package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner vehicleTypeSpinner;
    private EditText vehicleNumberEditText, rcNumberEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        vehicleTypeSpinner = findViewById(R.id.vehicleTypeSpinner);
        vehicleNumberEditText = findViewById(R.id.vehicleNumberEditText);
        rcNumberEditText = findViewById(R.id.rcNumberEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vehicleType = vehicleTypeSpinner.getSelectedItem().toString();
                String vehicleNumber = vehicleNumberEditText.getText().toString();
                String rcNumber = rcNumberEditText.getText().toString();

                if (!vehicleNumber.isEmpty() && !rcNumber.isEmpty()) {
                    // Launch the confirmation activity to display entered details
                    Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                    intent.putExtra("vehicleType", vehicleType);
                    intent.putExtra("vehicleNumber", vehicleNumber);
                    intent.putExtra("rcNumber", rcNumber);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}