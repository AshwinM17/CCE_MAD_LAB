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

public class ArtistsFragment extends Fragment {

    // onCreateView is called when it's time to create the view for this fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment, fragment_list.xml, which contains the ListView
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Find the ListView from the layout so that we can populate it with data
        ListView listView = view.findViewById(R.id.listView);

        // Create a list of artist names to display in the ListView
        List<String> artists = Arrays.asList("Eminem", "Drake", "The Weeknd", "Taylor Swift", "Billie Eilish");

        // Create an ArrayAdapter to bind the list of artists to the ListView
        // android.R.layout.simple_list_item_1 is a built-in layout that displays each artist name in a simple text format
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, artists);

        // Set the adapter to the ListView so the artist names are displayed
        listView.setAdapter(adapter);

        // Return the view to be displayed in the fragment
        return view;
    }
}