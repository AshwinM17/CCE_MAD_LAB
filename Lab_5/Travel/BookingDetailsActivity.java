package com.example.travel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {

    private TextView tvSource, tvDestination, tvDate, tvTicketType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        // Initialize UI elements
        tvSource = findViewById(R.id.tvSource);
        tvDestination = findViewById(R.id.tvDestination);
        tvDate = findViewById(R.id.tvDate);
        tvTicketType = findViewById(R.id.tvTicketType);

        // Retrieve data passed from MainActivity
        String source = getIntent().getStringExtra("source");
        String destination = getIntent().getStringExtra("destination");
        String dateOfTravel = getIntent().getStringExtra("dateOfTravel");
        String ticketType = getIntent().getStringExtra("ticketType");

        // Display the booking details
        tvSource.setText("Source: " + source);
        tvDestination.setText("Destination: " + destination);
        tvDate.setText("Date of Travel: " + dateOfTravel);
        tvTicketType.setText("Ticket Type: " + ticketType);
    }
}