package com.example.movie;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {

    private TextView tvMovie, tvTheatre, tvDate, tvTime, tvTicketType, tvSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        tvMovie = findViewById(R.id.tvMovie);
        tvTheatre = findViewById(R.id.tvTheatre);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvTicketType = findViewById(R.id.tvTicketType);
        tvSeats = findViewById(R.id.tvSeats);

        // Retrieve data passed from MainActivity
        String movie = getIntent().getStringExtra("movie");
        String theatre = getIntent().getStringExtra("theatre");
        String dateOfShow = getIntent().getStringExtra("dateOfShow");
        String timeOfShow = getIntent().getStringExtra("timeOfShow");
        String ticketType = getIntent().getStringExtra("ticketType");
        int availableSeats = getIntent().getIntExtra("availableSeats", 0);

        // Display the booking details
        tvMovie.setText("Movie: " + movie);
        tvTheatre.setText("Theatre: " + theatre);
        tvDate.setText("Date: " + dateOfShow);
        tvTime.setText("Time: " + timeOfShow);
        tvTicketType.setText("Ticket Type: " + ticketType);
        tvSeats.setText("Available Seats: " + availableSeats);
    }
}