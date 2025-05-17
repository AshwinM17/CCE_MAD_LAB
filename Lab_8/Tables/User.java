package com.example.myapplication;

public class User {
    private int id; // User ID (primary key)
    private String name; // User's name
    private String email; // User's email

    // Default constructor (required for SQLite database operations)
    public User() {}

    // Parameterized constructor to create a User with a name and email
    public User(String name, String email) {
        this.name = name; // Set the user's name
        this.email = email; // Set the user's email
    }

    // Getter for the user's ID
    public int getId() {
        return id;
    }

    // Setter for the user's ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for the user's name
    public String getName() {
        return name;
    }

    // Setter for the user's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the user's email
    public String getEmail() {
        return email;
    }

    // Setter for the user's email
    public void setEmail(String email) {
        this.email = email;
    }
}
