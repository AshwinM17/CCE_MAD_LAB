package com.example.travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerSource, spinnerDestination;
    private DatePicker datePickerTravel;
    private ToggleButton toggleTicketType;
    private Button btnSubmit, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable edge-to-edge display as in the provided code snippet
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        spinnerSource = findViewById(R.id.spinnerSource);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        datePickerTravel = findViewById(R.id.datePickerTravel);
        toggleTicketType = findViewById(R.id.toggleTicketType);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);

        // Set up spinners using an array of locations defined in res/values/arrays.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);

        // Handle the Submit button click: gather input and start the details activity
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String source = spinnerSource.getSelectedItem().toString();
                String destination = spinnerDestination.getSelectedItem().toString();
                int day = datePickerTravel.getDayOfMonth();
                int month = datePickerTravel.getMonth();
                int year = datePickerTravel.getYear();

                String dateOfTravel = day + "/" + (month + 1) + "/" + year;
                String ticketType = toggleTicketType.isChecked() ? "Round Trip" : "One Way";

                // Create an intent to launch BookingDetailsActivity
                Intent intent = new Intent(MainActivity.this, BookingDetailsActivity.class);
                intent.putExtra("source", source);
                intent.putExtra("destination", destination);
                intent.putExtra("dateOfTravel", dateOfTravel);
                intent.putExtra("ticketType", ticketType);
                startActivity(intent);
            }
        });

        // Handle the Reset button click: clear input fields and reset DatePicker
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Reset spinners to their first item
                spinnerSource.setSelection(0);
                spinnerDestination.setSelection(0);

                // Reset DatePicker to current date
                Calendar calendar = Calendar.getInstance();
                datePickerTravel.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                // Reset ToggleButton to default state (unchecked = One Way)
                toggleTicketType.setChecked(false);
            }
        });
    }
}