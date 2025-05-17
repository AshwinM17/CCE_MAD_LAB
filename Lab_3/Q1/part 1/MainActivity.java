package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the layout for the activity

        // Find references to the TabLayout and ViewPager2
        TabLayout tabLayout = findViewById(R.id.tabLayout);  // TabLayout reference
        ViewPager2 viewPager = findViewById(R.id.viewPager);  // ViewPager2 reference

        // Create the adapter for the ViewPager2
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);  // Set the adapter for the ViewPager2

        // Link the TabLayout with the ViewPager2 using the TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Set the title for each tab based on the position
            switch (position) {
                case 0:
                    tab.setText("Tab 1");
                    break;
                case 1:
                    tab.setText("Tab 2");
                    break;
                case 2:
                    tab.setText("Tab 3");
                    break;
                default:
                    tab.setText("Tab 1");
                    break;
            }
        }).attach();  // Attach the mediator to link TabLayout with ViewPager2
    }
}
