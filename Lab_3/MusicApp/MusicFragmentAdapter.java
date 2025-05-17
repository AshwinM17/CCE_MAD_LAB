package com.example.musicapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MusicFragmentAdapter extends FragmentStateAdapter {

    // Constructor that takes the FragmentActivity to associate the adapter with it
    public MusicFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity); // Pass the FragmentActivity to the superclass
    }

    // This method is used to create a fragment for the given position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Switch based on the position to return the corresponding fragment
        switch (position) {
            case 0: // If position is 0, return the ArtistsFragment
                return new ArtistsFragment();
            case 1: // If position is 1, return the AlbumsFragment
                return new AlbumsFragment();
            case 2: // If position is 2, return the SongsFragment
                return new SongsFragment();
            default: // Default case to handle invalid position (though not expected to happen)
                return new ArtistsFragment(); // Fallback to ArtistsFragment
        }
    }

    // This method returns the total number of items (fragments) in the adapter
    @Override
    public int getItemCount() {
        return 3; // We have 3 fragments: Artists, Albums, Songs
    }
}