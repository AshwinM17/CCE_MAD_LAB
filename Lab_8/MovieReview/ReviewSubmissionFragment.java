package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReviewSubmissionFragment extends Fragment {
    // UI components
    private EditText movieNameEditText;
    private EditText yearEditText;
    private RatingBar pointsRatingBar;
    private EditText reviewEditText;
    private Button submitButton;

    // Database helper instance
    private MovieDatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_submission, container, false);

        // Initialize UI elements
        movieNameEditText = view.findViewById(R.id.movieNameEditText);
        yearEditText = view.findViewById(R.id.yearEditText);
        pointsRatingBar = view.findViewById(R.id.pointsRatingBar);
        reviewEditText = view.findViewById(R.id.reviewEditText);
        submitButton = view.findViewById(R.id.submitButton);

        // Initialize database helper
        databaseHelper = new MovieDatabaseHelper(requireContext());

        // Set up submit button click listener
        submitButton.setOnClickListener(v -> submitReview());

        return view;
    }

    /**
     * Handles the submission of a movie review.
     */
    private void submitReview() {
        // Retrieve input values
        String movieName = movieNameEditText.getText().toString().trim();
        String yearStr = yearEditText.getText().toString().trim();
        float points = pointsRatingBar.getRating();
        String reviewText = reviewEditText.getText().toString().trim();

        // Validate inputs
        if (movieName.isEmpty()) {
            movieNameEditText.setError("Movie name is required");
            return;
        }

        if (yearStr.isEmpty()) {
            yearEditText.setError("Year is required");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr); // Convert year string to integer
        } catch (NumberFormatException e) {
            yearEditText.setError("Invalid year");
            return;
        }

        // Save review to the database

        // Save to database
        long  result = databaseHelper.addMovieReview(movieName, year, points, reviewText);

        if (result != -1) {
            Toast.makeText(requireContext(), "Review submitted successfully!", Toast.LENGTH_SHORT).show();
            // Clear inputs
            movieNameEditText.setText("");
            yearEditText.setText("");
            pointsRatingBar.setRating(0);
            reviewEditText.setText("");

            // Refresh the list of movies in the ReviewViewFragment
            ((MainActivity) getActivity()).refreshMovieList(); // Assuming MainActivity is hosting the fragments
        } else {
            Toast.makeText(requireContext(), "Failed to submit review", Toast.LENGTH_SHORT).show();
        }
    }
}