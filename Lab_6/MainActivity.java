package com.example.fitness;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up toolbar if you have one (optional)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Reference to the content container's TextView
        contentText = findViewById(R.id.content_text);
        // Set default content for homepage
        contentText.setText("Welcome to XYZ Fitness Center");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate our menu resource (contains both text and icon items)
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.action_workout) {
            // For "Workout Plans": display list of workout programs
            contentText.setText("Workout Plans:\n- Weight Loss\n- Cardio\n- Strength Training");
            return true;
        } else if (id == R.id.action_trainers) {
            // For "Trainers": display trainer names, specializations, and sample photos
            contentText.setText("Trainers:\nJohn Doe - Strength & Conditioning\nJane Smith - Cardio & Endurance");
            // In a complete app, you would likely replace this text with a RecyclerView or Fragment
            return true;
        } else if (id == R.id.action_membership) {
            // For "Membership": display packages and pricing details
            contentText.setText("Membership Packages:\n- Basic: $30/month\n- Premium: $50/month\n- VIP: $80/month");
            return true;
        } else if (id == R.id.action_home) {
            // Icon: Homepage
            contentText.setText("Welcome to XYZ Fitness Center");
            return true;
        } else if (id == R.id.action_contact) {
            // Icon: Contact US
            contentText.setText("Contact Us at:\nPhone: (123) 456-7890\nEmail: contact@xyzfitness.com");
            return true;
        } else if (id == R.id.action_about) {
            // Icon: About Us
            contentText.setText("About Us:\nXYZ Fitness Center is dedicated to helping you achieve your fitness goals.");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}