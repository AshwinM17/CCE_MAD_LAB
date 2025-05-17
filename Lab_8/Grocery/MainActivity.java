package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private GroceryDatabaseHelper databaseHelper;
    private Spinner itemSpinner;
    private EditText priceEditText;
    private Button addItemButton;
    private ListView selectedItemsListView;
    private TextView totalCostTextView;

    private List<String> availableItems;
    private List<String> selectedItems;
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayAdapter<String> listAdapter;

    private double totalCost = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize database helper
        databaseHelper = new GroceryDatabaseHelper(this);

        // Initialize views
        itemSpinner = findViewById(R.id.itemSpinner);
        priceEditText = findViewById(R.id.priceEditText);
        addItemButton = findViewById(R.id.addItemButton);
        selectedItemsListView = findViewById(R.id.selectedItemsListView);
        totalCostTextView = findViewById(R.id.totalCostTextView);

        // Initialize lists
        selectedItems = new ArrayList<>();
        availableItems = databaseHelper.getAllGroceryItemNames();

        // If no items in database, add default items
        if (availableItems.isEmpty()) {
            databaseHelper.addGroceryItem("Milk", 3.50);
            databaseHelper.addGroceryItem("Bread", 2.25);
            databaseHelper.addGroceryItem("Eggs", 4.00);
            databaseHelper.addGroceryItem("Cheese", 5.75);
            availableItems = databaseHelper.getAllGroceryItemNames();
        }

        // Setup spinner adapter
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, availableItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(spinnerAdapter);

        // Setup list adapter for selected items
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedItems);
        selectedItemsListView.setAdapter(listAdapter);

        // Auto-fill price when item is selected
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = availableItems.get(position);
                double price = databaseHelper.getItemPrice(selectedItem);
                priceEditText.setText(String.format(Locale.getDefault(), "%.2f", price));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Add item button click listener
        addItemButton.setOnClickListener(v -> addSelectedItem());
    }

    private void addSelectedItem() {
        String selectedItem = (String) itemSpinner.getSelectedItem();
        String priceStr = priceEditText.getText().toString();

        if (priceStr.isEmpty()) {
            Toast.makeText(this, "Please enter a price", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);

        // Add to selected items list
        String itemEntry = String.format(Locale.getDefault(), "%s - $%.2f", selectedItem, price);
        selectedItems.add(itemEntry);
        listAdapter.notifyDataSetChanged();

        // Update total cost
        totalCost += price;
        updateTotalCost();

        // Clear price input
        priceEditText.setText("");
    }

    private void updateTotalCost() {
        totalCostTextView.setText(String.format(Locale.getDefault(), "Total Cost: $%.2f", totalCost));
    }
}
