package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleObserver;

public class MainActivity  extends AppCompatActivity{

    private TextView myTextView;
    private Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the TextView
        myTextView = findViewById(R.id.myTextView);

        myButton = findViewById(R.id.button);

        myButton.setOnClickListener(v -> {
            // Change the text of the TextView when the button is clicked
            myTextView.setText("Button Pressed");
//            onStop();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onStop() {
        // Call the superclass method first.
        super.onStop();
        if (myTextView != null) {
            myTextView.setText("Activity Stopped");
        }

    }

}