package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout; // Tab layout to hold tabs
    private ViewPager2 viewPager; // ViewPager2 to switch between fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables full-screen edge-to-edge UI
        setContentView(R.layout.activity_main);

        // Handles system window insets (for better UI adaptation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Set adapter for ViewPager2
        viewPager.setAdapter(new MovieReviewPagerAdapter(this));

        // Connect TabLayout with ViewPager2 and set tab titles
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Submit Review"); // First tab for submitting reviews
                            break;
                        case 1:
                            tab.setText("View Reviews"); // Second tab for viewing reviews
                            break;
                    }
                }
        ).attach();
    }

    /**
     * Adapter class for ViewPager2, manages fragments for each tab.
     */
    private class MovieReviewPagerAdapter extends FragmentStateAdapter {
        public MovieReviewPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public Fragment createFragment(int position) {
            // Returns the corresponding fragment for each tab
            switch (position) {
                case 0:
                    return new ReviewSubmissionFragment(); // First tab: Review submission
                case 1:
                    return new ReviewViewFragment(); // Second tab: Review display
                default:
                    return new ReviewSubmissionFragment(); // Default case (should never happen)
            }
        }

        @Override
        public int getItemCount() {
            return 2; // Two tabs: Submit Review and View Reviews
        }
    }
    public void refreshMovieList() {
        // Find the ReviewViewFragment and call its populateMovieList method to refresh the list
        ReviewViewFragment reviewViewFragment = (ReviewViewFragment) getSupportFragmentManager().findFragmentByTag("f" + 1); // Fragment with position 1
        if (reviewViewFragment != null) {
            reviewViewFragment.populateMovieList(); // Refresh the ListView
        }
    }
}

