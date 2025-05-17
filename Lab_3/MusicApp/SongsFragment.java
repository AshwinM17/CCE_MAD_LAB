package com.example.musicapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Arrays;
import java.util.List;

public class SongsFragment extends Fragment {

    // onCreateView is called when the fragment's view is being created
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the fragment layout (fragment_list.xml) into a view object
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Find the ListView in the inflated view
        ListView listView = view.findViewById(R.id.listView);

        // Create a list of songs to be displayed in the ListView
        List<String> songs = Arrays.asList("Lose Yourself", "God's Plan", "Blinding Lights", "Shake It Off", "Bad Guy");

        // Create an ArrayAdapter to convert the list of songs into a format that can be displayed in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, songs);

        // Set the ArrayAdapter to the ListView so that it will display the songs
        listView.setAdapter(adapter);

        // Return the view for the fragment
        return view;
    }
}