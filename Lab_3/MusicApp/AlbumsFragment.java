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

public class AlbumsFragment extends Fragment {

    // onCreateView is called when it's time to create the view for this fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment, fragment_list.xml, which contains the ListView
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Find the ListView in the layout
        ListView listView = view.findViewById(R.id.listView);

        // Create a list of album names
        List<String> albums = Arrays.asList("Recovery", "Certified Lover Boy", "After Hours", "1989", "Happier Than Ever");

        // Create an ArrayAdapter to bind the list of albums to the ListView
        // android.R.layout.simple_list_item_1 is a built-in layout that displays a single text item
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, albums);

        // Set the adapter to the ListView so the data will be displayed
        listView.setAdapter(adapter);

        // Return the view to display
        return view;
    }
}