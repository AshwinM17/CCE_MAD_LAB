package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {
    private int id;
    private String name;
    private Date dueDate;
    private String priority;

    public Task(int id, String name, Date dueDate, String priority) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public Task(String name, Date dueDate, String priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getFormattedDueDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(dueDate);
    }
}
