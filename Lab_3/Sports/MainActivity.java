package com.example.sports;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_main layout
        setContentView(R.layout.activity_main);

        // Find the ListView widget from the layout using its ID
        ListView sportsList = findViewById(R.id.sportsList);

        // Sample sports list to display in the ListView
        String[] sports = {"Football", "Basketball", "Tennis", "Cricket", "Hockey", "Baseball", "Badminton"};

        // Create an ArrayAdapter to bind the sports array to the ListView
        // Use the simple_list_item_1 layout resource for each item in the list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sports);

        // Set the adapter to the ListView so that it can display the sports items
        sportsList.setAdapter(adapter);

        // Set an item click listener for the ListView to handle clicks on individual items
        sportsList.setOnItemClickListener((parent, view, position, id) -> {
            // Get the selected sport text from the clicked ListView item
            String selectedSport = ((TextView) view).getText().toString();

            // Display a Toast message showing the selected sport
            Toast.makeText(MainActivity.this, "Selected Sport: " + selectedSport, Toast.LENGTH_SHORT).show();
        });
    }
}