package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView resultTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);  // Use the correct layout

        resultTextView = findViewById(R.id.resultTextView);
        backButton = findViewById(R.id.backButton);

        // Get the computed result passed from MainActivity
        float result = getIntent().getFloatExtra("result", 0f);  // Retrieve the result

        // Check if the result is valid (not 0f) and display it
        if (result != 0f) {
            resultTextView.setText("Result: " + result);  // Set the result to the TextView
        } else {
            resultTextView.setText("Invalid Expression");  // In case the result is 0f or invalid
        }

        // Set up the back button to navigate back to MainActivity
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
