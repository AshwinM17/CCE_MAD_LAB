package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDatabase"; // Database name
    private static final int DATABASE_VERSION = 1; // Database version

    // Table and column names
    public static final String TABLE_USERS = "users"; // Table name
    public static final String COLUMN_ID = "id"; // Column for user ID
    public static final String COLUMN_NAME = "name"; // Column for user name
    public static final String COLUMN_EMAIL = "email"; // Column for user email

    // Create table SQL query
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_EMAIL + " TEXT)";

    // Constructor for the DatabaseHelper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // Call the parent constructor
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the SQL query to create the users table
        db.execSQL(CREATE_USERS_TABLE);
    }

    // Called when the database needs to be upgraded (e.g., version change)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create the table again
        onCreate(db);
    }

    // Method to insert a new user into the database
    public long insertUser(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable database

        ContentValues values = new ContentValues(); // Create a ContentValues object to hold the values
        values.put(COLUMN_NAME, name); // Put the name into the ContentValues
        values.put(COLUMN_EMAIL, email); // Put the email into the ContentValues

        // Insert the row into the database and return the row ID
        return db.insert(TABLE_USERS, null, values);
    }

    // Method to get all users from the database
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>(); // Create a list to hold the users

        // Select all query to fetch users from the database
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase(); // Get a readable database
        Cursor cursor = db.rawQuery(selectQuery, null); // Execute the query

        // Loop through all rows in the result set and add them to the list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(); // Create a new User object
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))); // Set the user's ID
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME))); // Set the user's name
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))); // Set the user's email

                userList.add(user); // Add the user to the list
            } while (cursor.moveToNext()); // Continue if there are more rows
        }

        cursor.close(); // Close the cursor
        return userList; // Return the list of users
    }

    // Method to update a user's details in the database
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable database

        ContentValues values = new ContentValues(); // Create a ContentValues object to hold the updated values
        values.put(COLUMN_NAME, user.getName()); // Update the name
        values.put(COLUMN_EMAIL, user.getEmail()); // Update the email

        // Update the row with the specified user ID and return the number of rows affected
        return db.update(TABLE_USERS, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    // Method to delete a user from the database
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get a writable database

        // Delete the user row with the specified ID
        db.delete(TABLE_USERS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });

        db.close(); // Close the database connection
    }
}
