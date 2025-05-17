package com.example.newsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class NewsPagerAdapter extends FragmentStateAdapter {

    // Constructor to initialize the adapter with the fragment activity (MainActivity)
    public NewsPagerAdapter(@NonNull MainActivity fragmentActivity) {
        super(fragmentActivity); // Pass the fragment activity to the superclass
    }

    // This method is responsible for creating the fragment based on the position (tab selected)
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Variable to store the fragment to be created
        Fragment fragment;

        // Switch statement to decide which fragment to create based on the position (tab)
        switch (position) {
            case 0:
                // If the first tab is selected, create a NewsFragment with category "Top Stories"
                fragment = new NewsFragment("Top Stories");
                break;
            case 1:
                // If the second tab is selected, create a NewsFragment with category "Sports"
                fragment = new NewsFragment("Sports");
                break;
            case 2:
                // If the third tab is selected, create a NewsFragment with category "Entertainment"
                fragment = new NewsFragment("Entertainment");
                break;
            default:
                // Default case for any other position (though it shouldn't occur), creating "Top Stories"
                fragment = new NewsFragment("Top Stories");
        }

        // Return the created fragment
        return fragment;
    }

    // This method returns the total number of tabs (fragments) that are available
    @Override
    public int getItemCount() {
        // There are three tabs: Top Stories, Sports, and Entertainment
        return 3;
    }
}