package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class NewsFragment extends Fragment {

    private String category; // Variable to store the category of news (e.g., Top Stories, Sports, etc.)

    // Constructor to pass the category to this fragment
    public NewsFragment(String category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // The fragment_news layout file is inflated into the fragment view
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // Set the category title dynamically based on the category passed to the fragment
        TextView categoryTitle = view.findViewById(R.id.categoryTitle);
        categoryTitle.setText(category); // Set the category name to the TextView (e.g., Top Stories, Sports, etc.)

        // Dummy data for the news content. This will be replaced with real news data in a real app
        TextView newsContent = view.findViewById(R.id.newsContent);

        // Prepare a dummy news string that simulates the actual content for the selected category
        String dummyNews = "1. News item about " + category + "\n2. Another news item about " + category + "\n3. More news on " + category;
        newsContent.setText(dummyNews); // Set the dummy news content in the TextView

        return view; // Return the inflated view
    }
}