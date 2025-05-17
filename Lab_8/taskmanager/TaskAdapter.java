package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

// Custom adapter for displaying Task objects in a ListView
public class TaskAdapter extends ArrayAdapter<Task> {

    // Constructor to initialize the adapter with context and list of tasks
    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks); // Call the superclass constructor with context and task list
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the task object for the current position in the list
        Task task = getItem(position);

        // Check if a reusable view (convertView) exists, if not, inflate a new layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_task, parent, false);
        }

        // Find UI elements in the list item layout
        TextView tvTaskName = convertView.findViewById(R.id.tvTaskName);
        TextView tvDueDate = convertView.findViewById(R.id.tvDueDate);
        TextView tvPriority = convertView.findViewById(R.id.tvPriority);

        // Populate the UI elements with task data
        tvTaskName.setText(task.getName()); // Set task name
        tvDueDate.setText("Due: " + task.getFormattedDueDate()); // Set formatted due date
        tvPriority.setText(task.getPriority()); // Set priority level text

        // Change text color based on priority level
        switch (task.getPriority()) {
            case "High":
                tvPriority.setTextColor(Color.RED); // High priority = Red
                break;
            case "Medium":
                tvPriority.setTextColor(Color.rgb(255, 165, 0)); // Medium priority = Orange
                break;
            case "Low":
                tvPriority.setTextColor(Color.GREEN); // Low priority = Green
                break;
        }

        // Return the completed view for display in the ListView
        return convertView;
    }
}
