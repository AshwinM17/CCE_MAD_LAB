package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Locale;

public class ReviewViewFragment extends Fragment {
    private ListView movieListView;  // ListView to display the list of movies
    private TextView movieNameTextView;  // TextView to display the selected movie's name
    private TextView yearTextView;  // TextView to display the selected movie's year
    private TextView pointsTextView;  // TextView to display the selected movie's rating points
    private TextView reviewTextView;  // TextView to display the selected movie's review
    private MovieDatabaseHelper databaseHelper;  // Database helper for interacting with the SQLite database

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_review_view, container, false);

        // Initialize the views from the layout
        movieListView = view.findViewById(R.id.movieListView);
        movieNameTextView = view.findViewById(R.id.movieNameTextView);
        yearTextView = view.findViewById(R.id.yearTextView);
        pointsTextView = view.findViewById(R.id.pointsTextView);
        reviewTextView = view.findViewById(R.id.reviewTextView);

        // Initialize the database helper
        databaseHelper = new MovieDatabaseHelper(requireContext());

        // Populate the ListView with movie names from the database
        populateMovieList();

        // Set up an item click listener for the ListView to show the details of the selected movie
        movieListView.setOnItemClickListener(this::onMovieSelected);

        return view;
    }

    void populateMovieList() {
        // Retrieve all movie names from the database
        List<String> movieNames = databaseHelper.getAllMovieNames();

        // Create an ArrayAdapter to display the movie names in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, movieNames);

        // Set the adapter to the ListView, notify adapter about data change
        movieListView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // Notify the adapter to refresh the list
    }


    // This method is triggered when an item in the ListView is clicked
    private void onMovieSelected(AdapterView<?> parent, View view, int position, long id) {
        // Get the selected movie name from the ListView
        String selectedMovie = (String) parent.getItemAtPosition(position);

        // Retrieve the review details for the selected movie from the database
        MovieDatabaseHelper.MovieReview review = databaseHelper.getMovieReviewDetails(selectedMovie);

        if (review != null) {
            // Display the movie review details in the TextViews
            movieNameTextView.setText(String.format("Movie: %s", review.movieName));
            yearTextView.setText(String.format("Year: %d", review.year));
            pointsTextView.setText(String.format(Locale.getDefault(), "Points: %.1f/5", review.points));
            reviewTextView.setText(String.format("Review: %s", review.reviewText));
        }
    }
}
