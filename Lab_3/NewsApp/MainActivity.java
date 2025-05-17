package com.example.newsapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the TabLayout from the layout
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Initialize the ViewPager2 from the layout
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // Set up the ViewPager adapter
        NewsPagerAdapter adapter = new NewsPagerAdapter(this);
        viewPager.setAdapter(adapter); // Set the adapter for the ViewPager

        // Link the TabLayout with the ViewPager and set tab titles for each page
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    // Set the tab title for the first tab (Top Stories)
                    tab.setText("Top Stories");
                    break;
                case 1:
                    // Set the tab title for the second tab (Sports)
                    tab.setText("Sports");
                    break;
                case 2:
                    // Set the tab title for the third tab (Entertainment)
                    tab.setText("Entertainment");
                    break;
            }
        }).attach(); // Attach the TabLayoutMediator to sync the TabLayout with the ViewPager
    }
}