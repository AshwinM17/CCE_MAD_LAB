package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper; // Database helper instance for handling database operations
    private EditText editTextName, editTextEmail; // Input fields for user name and email
    private Button buttonAdd, buttonUpdate, buttonDelete; // Buttons for adding, updating, and deleting users
    private ListView listViewUsers; // ListView to display the list of users
    private List<User> userList; // List to hold the user objects
    private int selectedUserId = -1; // Variable to keep track of the selected user's ID for update or delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display (full-screen layout)
        setContentView(R.layout.activity_main); // Set the activity layout

        // Set window insets to handle system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the database helper object
        dbHelper = new DatabaseHelper(this);

        // Initialize UI components (EditTexts, Buttons, ListView)
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        listViewUsers = findViewById(R.id.listViewUsers);

        // Set up click listener for the Add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input data from EditText fields
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();

                // Check if inputs are not empty
                if (!name.isEmpty() && !email.isEmpty()) {
                    long result = dbHelper.insertUser(name, email); // Insert the user into the database
                    if (result != -1) {
                        Toast.makeText(MainActivity.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                        refreshUserList(); // Refresh the ListView with the updated list of users
                        clearInputs(); // Clear the input fields
                    }
                }
            }
        });

        // Set up click listener for the Update button
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a user is selected for updating
                if (selectedUserId != -1) {
                    User user = new User();
                    user.setId(selectedUserId); // Set the ID of the user to be updated
                    user.setName(editTextName.getText().toString()); // Set the new name
                    user.setEmail(editTextEmail.getText().toString()); // Set the new email

                    int rowsAffected = dbHelper.updateUser(user); // Update the user in the database
                    if (rowsAffected > 0) {
                        Toast.makeText(MainActivity.this, "User Updated Successfully", Toast.LENGTH_SHORT).show();
                        refreshUserList(); // Refresh the user list in the ListView
                        clearInputs(); // Clear the input fields
                    }
                }
            }
        });

        // Set up click listener for the Delete button
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a user is selected for deletion
                if (selectedUserId != -1) {
                    User user = new User();
                    user.setId(selectedUserId); // Set the ID of the user to be deleted

                    dbHelper.deleteUser(user); // Delete the user from the database
                    Toast.makeText(MainActivity.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                    refreshUserList(); // Refresh the ListView to show the updated list
                    clearInputs(); // Clear the input fields
                }
            }
        });

        // Set up ListView item click listener for selecting a user for update/delete
        listViewUsers.setOnItemClickListener((parent, view, position, id) -> {
            User selectedUser = userList.get(position); // Get the selected user
            selectedUserId = selectedUser.getId(); // Set the selected user's ID for future operations
            // Populate input fields with selected user's details for update
            editTextName.setText(selectedUser.getName());
            editTextEmail.setText(selectedUser.getEmail());
        });

        // Populate the user list initially
        refreshUserList();
    }

    // Method to refresh the user list in the ListView
    private void refreshUserList() {
        userList = dbHelper.getAllUsers(); // Retrieve all users from the database

        // Create a list of maps to pass to the SimpleAdapter
        List<Map<String, String>> data = new ArrayList<>();
        for (User user : userList) {
            Map<String, String> map = new HashMap<>();
            map.put("name", user.getName()); // Store the user's name
            map.put("email", user.getEmail()); // Store the user's email
            data.add(map); // Add the user details to the data list
        }

        // Create a SimpleAdapter to bind the data to the ListView
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                android.R.layout.simple_list_item_2, // Layout for each item
                new String[]{"name", "email"}, // Keys to extract from data
                new int[]{android.R.id.text1, android.R.id.text2} // TextView IDs to bind data to
        );

        listViewUsers.setAdapter(adapter); // Set the adapter to the ListView
    }

    // Method to clear input fields after operation (Add/Update/Delete)
    private void clearInputs() {
        editTextName.setText(""); // Clear the name input field
        editTextEmail.setText(""); // Clear the email input field
        selectedUserId = -1; // Reset the selected user ID
    }
}
