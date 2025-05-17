package com.example.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMovie, spinnerTheatre;
    private DatePicker datePickerShow;
    private TimePicker timePickerShow;
    private ToggleButton toggleTicketType;
    private Button btnBookNow, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        spinnerMovie = findViewById(R.id.spinnerMovie);
        spinnerTheatre = findViewById(R.id.spinnerTheatre);
        datePickerShow = findViewById(R.id.datePickerShow);
        timePickerShow = findViewById(R.id.timePickerShow);
        toggleTicketType = findViewById(R.id.toggleTicketType);
        btnBookNow = findViewById(R.id.btnBookNow);
        btnReset = findViewById(R.id.btnReset);

        // Populate spinners from arrays.xml resources
        ArrayAdapter<CharSequence> movieAdapter = ArrayAdapter.createFromResource(this,
                R.array.movies, android.R.layout.simple_spinner_item);
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMovie.setAdapter(movieAdapter);

        ArrayAdapter<CharSequence> theatreAdapter = ArrayAdapter.createFromResource(this,
                R.array.theatres, android.R.layout.simple_spinner_item);
        theatreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTheatre.setAdapter(theatreAdapter);

        // Set TimePicker to 12-hour mode (or 24-hour if preferred)
        timePickerShow.setIs24HourView(false);

        // Initially enable Book Now (subject to further checks)
        btnBookNow.setEnabled(true);

        // Listen for changes on the TimePicker to check premium ticket timing
        timePickerShow.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            if (toggleTicketType.isChecked()) { // Premium selected
                if (hourOfDay < 12) {
                    btnBookNow.setEnabled(false);
                    Toast.makeText(MainActivity.this,
                            "Premium tickets require a showtime after 12:00 PM", Toast.LENGTH_SHORT).show();
                } else {
                    btnBookNow.setEnabled(true);
                }
            }
        });

        // Listen for ToggleButton changes
        toggleTicketType.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) { // Premium selected
                int hour = timePickerShow.getHour();
                if (hour < 12) {
                    btnBookNow.setEnabled(false);
                    Toast.makeText(MainActivity.this,
                            "Premium tickets require a showtime after 12:00 PM", Toast.LENGTH_SHORT).show();
                } else {
                    btnBookNow.setEnabled(true);
                }
            } else {
                btnBookNow.setEnabled(true);
            }
        });

        // Book Now button click listener
        btnBookNow.setOnClickListener(v -> {
            // Validate for premium: ensure selected time is after 12:00 PM
            if (toggleTicketType.isChecked() && timePickerShow.getHour() < 12) {
                Toast.makeText(MainActivity.this,
                        "Please select a showtime after 12:00 PM for Premium tickets", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gather values from UI
            String movie = spinnerMovie.getSelectedItem().toString();
            String theatre = spinnerTheatre.getSelectedItem().toString();

            // Get date from DatePicker (month is 0-indexed)
            int day = datePickerShow.getDayOfMonth();
            int month = datePickerShow.getMonth();
            int year = datePickerShow.getYear();
            String dateOfShow = day + "/" + (month + 1) + "/" + year;

            // Get time from TimePicker
            int hour = timePickerShow.getHour();
            int minute = timePickerShow.getMinute();
            String timeOfShow = String.format("%02d:%02d", hour, minute);

            // Determine ticket type
            String ticketType = toggleTicketType.isChecked() ? "Premium" : "Standard";

            // Dummy available seats calculation (could be dynamic in a real app)
            int availableSeats = 50;

            // Start the BookingDetailsActivity and pass the details
            Intent intent = new Intent(MainActivity.this, BookingDetailsActivity.class);
            intent.putExtra("movie", movie);
            intent.putExtra("theatre", theatre);
            intent.putExtra("dateOfShow", dateOfShow);
            intent.putExtra("timeOfShow", timeOfShow);
            intent.putExtra("ticketType", ticketType);
            intent.putExtra("availableSeats", availableSeats);
            startActivity(intent);
        });

        // Reset button click listener: clears all inputs and resets DatePicker to current date
        btnReset.setOnClickListener(v -> {
            spinnerMovie.setSelection(0);
            spinnerTheatre.setSelection(0);

            // Reset DatePicker to the current date
            Calendar calendar = Calendar.getInstance();
            datePickerShow.updateDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            // Reset TimePicker to current time
            timePickerShow.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePickerShow.setMinute(calendar.get(Calendar.MINUTE));

            // Reset ToggleButton to Standard (unchecked)
            toggleTicketType.setChecked(false);

            // Ensure Book Now is enabled
            btnBookNow.setEnabled(true);
        });
    }
}