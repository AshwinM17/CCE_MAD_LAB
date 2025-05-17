package com.example.musicapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    // onCreate is called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the layout for the activity

        // Find the TabLayout and ViewPager2 from the layout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // Initialize the adapter for ViewPager2 which will handle fragments
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);  // Set the adapter to the ViewPager2

        // Link the TabLayout with ViewPager2 and set the tab titles dynamically
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {  // Set the tab titles based on the position
                    switch (position) {
                        case 0: tab.setText("Artists"); break;  // Tab for Artists
                        case 1: tab.setText("Albums"); break;   // Tab for Albums
                        case 2: tab.setText("Songs"); break;    // Tab for Songs
                    }
                }).attach();  // Attach the mediator to link ViewPager and TabLayout
    }
}