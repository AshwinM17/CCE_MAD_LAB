package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private List<Task> taskList; // List to store tasks
    private TaskAdapter taskAdapter; // Custom adapter for displaying tasks in ListView
    private ListView listViewTasks; // ListView to display tasks
    private Button btnAddTask; // Button to add new tasks
    private TaskDatabaseHelper dbHelper;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TaskDatabaseHelper(this); // Initialize database helper

        // Initialize UI components
        listViewTasks = findViewById(R.id.listViewTasks);
        btnAddTask = findViewById(R.id.btnAddTask);

        // Load tasks from database
        taskList = dbHelper.getAllTasks();
        taskAdapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(taskAdapter);

        // Set click listener for "Add Task" button
        btnAddTask.setOnClickListener(v -> showAddTaskDialog(null, -1));

        // Set click listener for list items (to edit or delete tasks)
        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            showTaskOptionsDialog(position);
        });
    }


    // Method to show dialog for adding or editing a task
    private void showAddTaskDialog(Task taskToEdit, int position) {
        // Create an AlertDialog for task input
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);
        builder.setView(dialogView);

        // Initialize dialog UI components
        EditText editTextTaskName = dialogView.findViewById(R.id.editTextTaskName);
        DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
        Spinner spinnerPriority = dialogView.findViewById(R.id.spinnerPriority);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        // Setup priority spinner with values from resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.priority_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(adapter);

        // If editing an existing task, populate fields with existing data
        if (taskToEdit != null) {
            editTextTaskName.setText(taskToEdit.getName());

            // Set date picker to match the task's due date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(taskToEdit.getDueDate());
            datePicker.updateDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            // Set selected priority level in spinner
            String priority = taskToEdit.getPriority();
            int priorityPosition = 0;
            if (priority.equals("Medium")) priorityPosition = 1;
            else if (priority.equals("Low")) priorityPosition = 2;
            spinnerPriority.setSelection(priorityPosition);
        }

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Set click listener for save button
        btnSave.setOnClickListener(v -> {
            String taskName = editTextTaskName.getText().toString().trim();
            if (taskName.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a task name", Toast.LENGTH_SHORT).show();
                return;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            Date dueDate = calendar.getTime();
            String priority = spinnerPriority.getSelectedItem().toString();

            if (taskToEdit == null) {
                Task newTask = new Task(taskName, dueDate, priority);
                dbHelper.addTask(newTask);
                taskList.add(newTask);
                Toast.makeText(MainActivity.this, "Task added", Toast.LENGTH_SHORT).show();
            } else {
                taskToEdit.setName(taskName);
                taskToEdit.setDueDate(dueDate);
                taskToEdit.setPriority(priority);
                dbHelper.updateTask(taskToEdit);
                Toast.makeText(MainActivity.this, "Task updated", Toast.LENGTH_SHORT).show();
            }

            taskAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });


        // Set click listener for cancel button
        btnCancel.setOnClickListener(v -> dialog.dismiss());
    }

    // Method to show dialog for task options (Edit/Delete)
    private void showTaskOptionsDialog(int position) {
        Task selectedTask = taskList.get(position);

        // Create AlertDialog with options to edit or delete task
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Task Options")
                .setItems(new String[]{"Edit", "Delete"}, (dialog, which) -> {
                    if (which == 0) {
                        // If "Edit" is selected, open edit dialog
                        showAddTaskDialog(selectedTask, position);
                    } else {
                        // If "Delete" is selected, remove task from list
                        dbHelper.deleteTask(selectedTask.getId());
                        taskList.remove(position);
                        taskAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
}
