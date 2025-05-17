package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Key for SharedPreferences
    private static final String PREFS_NAME = "MyPrefsFile";

    // Keys for storing specific values
    private static final String NAME_KEY = "username";
    private static final String EMAIL_KEY = "email";
    private static final String AGE_KEY = "age";

    // UI Components
    private EditText editTextName, editTextEmail, editTextAge;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables edge-to-edge display to allow full-screen view
        setContentView(R.layout.activity_main);

        // Handle system bars insets to adjust UI for system UI like status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        initializeViews();

        // Load previously saved preferences when activity is created
        loadSavedPreferences();
    }

    private void initializeViews() {
        // Bind UI components to variables
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSave = findViewById(R.id.buttonSave);

        // Set up a click listener for the save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences(); // Save the preferences when button is clicked
            }
        });
    }

    // Method to save data into SharedPreferences
    private void savePreferences() {
        // Get SharedPreferences in edit mode
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Save the values from EditText fields
        editor.putString(NAME_KEY, editTextName.getText().toString());
        editor.putString(EMAIL_KEY, editTextEmail.getText().toString());

        // Check if age is provided and valid before saving
        String ageStr = editTextAge.getText().toString();
        if (!ageStr.isEmpty()) {
            try {
                // Try parsing age to integer
                int age = Integer.parseInt(ageStr);
                editor.putInt(AGE_KEY, age); // Save age if valid
            } catch (NumberFormatException e) {
                // Display error message if age is not a valid number
                Toast.makeText(this, "Invalid age", Toast.LENGTH_SHORT).show();
                return; // Return early to prevent saving invalid data
            }
        }

        // Commit the changes asynchronously
        editor.apply(); // Use apply() for asynchronous saving

        // Show a confirmation message
        Toast.makeText(this, "Preferences Saved", Toast.LENGTH_SHORT).show();
    }

    // Method to load previously saved preferences
    private void loadSavedPreferences() {
        // Retrieve SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Restore values from SharedPreferences (if available)
        String savedName = preferences.getString(NAME_KEY, "");
        String savedEmail = preferences.getString(EMAIL_KEY, "");
        int savedAge = preferences.getInt(AGE_KEY, -1); // Default value for age is -1

        // Set the restored values into EditText fields
        editTextName.setText(savedName);
        editTextEmail.setText(savedEmail);

        // Only set the age if it was previously saved
        if (savedAge != -1) {
            editTextAge.setText(String.valueOf(savedAge));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Optionally save preferences when the activity stops
        savePreferences(); // Automatically save when the activity is stopped
    }
}
