package com.example.musicapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    // Constructor that initializes the adapter with the FragmentActivity
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // This method is called to create a fragment for a specific position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Switch case to return the appropriate fragment based on the position
        switch (position) {
            case 0: // If the position is 0, return the ArtistsFragment
                return new ArtistsFragment();
            case 1: // If the position is 1, return the AlbumsFragment
                return new AlbumsFragment();
            case 2: // If the position is 2, return the SongsFragment
                return new SongsFragment();
            default: // In case of an unexpected position, return the ArtistsFragment as default
                return new ArtistsFragment();
        }
    }

    // This method returns the total number of fragments (in this case, 3)
    @Override
    public int getItemCount() {
        return 3; // The number of tabs (Artists, Albums, Songs)
    }
}