package com.example.toggledata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private Button buttonChangeMode;
    private ImageView imageViewMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        toggleButton = findViewById(R.id.toggleButton);
        buttonChangeMode = findViewById(R.id.button_change_mode);
        imageViewMode = findViewById(R.id.imageView_mode);

        // Set initial state
        updateMode();

        // Listener for the change mode button
        buttonChangeMode.setOnClickListener(v -> {
            // Switch the mode based on the ToggleButton state
            updateMode();
        });

        // Listener for the ToggleButton state change
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateMode();
        });
    }

    private void updateMode() {
        // Check the current state of the ToggleButton
        if (toggleButton.isChecked()) {
            // Mobile Data is selected
            imageViewMode.setImageResource(R.drawable.ic_mobile_data); // Image for Mobile Data
            Toast.makeText(this, "Mobile Data Mode", Toast.LENGTH_SHORT).show();
        } else {
            // Wi-Fi is selected
            imageViewMode.setImageResource(R.drawable.ic_wifi); // Image for Wi-Fi
            Toast.makeText(this, "Wi-Fi Mode", Toast.LENGTH_SHORT).show();
        }
    }
}