package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button setup
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            // Show custom Toast for Button click
            showCustomToast("Button Clicked!", R.drawable.button_image);
        });

        // ToggleButton setup
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Show a custom Toast based on ToggleButton state
            if (isChecked) {
                showCustomToast("Toggle ON!", R.drawable.toggle_on);
            } else {
                showCustomToast("Toggle OFF!", R.drawable.toggle_off);
            }
        });
    }

    // Method to display a custom Toast message with an image
    private void showCustomToast(String message, int imageResId) {
        // Inflate the custom Toast layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Set the image and message dynamically
        ImageView imageView = layout.findViewById(R.id.toast_image);
        imageView.setImageResource(imageResId);
        TextView textView = layout.findViewById(R.id.toast_message);
        textView.setText(message);

        // Create and show the Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout); // Set the custom layout as the view
        toast.show();
    }
}