package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Database helper class for managing movie reviews in an SQLite database.
 */
public class MovieDatabaseHelper extends SQLiteOpenHelper {
    // Database name and version
    private static final String DATABASE_NAME = "movie_reviews_db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_REVIEWS = "movie_reviews";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MOVIE_NAME = "movie_name";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_REVIEW_TEXT = "review_text";

    // SQL statement to create the movie reviews table
    private static final String CREATE_TABLE_REVIEWS =
            "CREATE TABLE " + TABLE_REVIEWS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," // Auto-incrementing primary key
                    + COLUMN_MOVIE_NAME + " TEXT," // Movie name
                    + COLUMN_YEAR + " INTEGER," // Release year
                    + COLUMN_POINTS + " REAL," // Rating points
                    + COLUMN_REVIEW_TEXT + " TEXT)"; // Review text

    /**
     * Constructor for the database helper.
     *
     * @param context The application context.
     */
    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is first created.
     *
     * @param db The database instance.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REVIEWS); // Execute SQL to create the table
    }

    /**
     * Called when the database needs to be upgraded.
     * This method drops the old table and creates a new one.
     *
     * @param db         The database instance.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS); // Delete old table
        onCreate(db); // Recreate the table
    }

    /**
     * Inserts a new movie review into the database.
     *
     * @param movieName  The name of the movie.
     * @param year       The release year of the movie.
     * @param points     The rating points given to the movie.
     * @param reviewText The review text.
     * @return The row ID of the newly inserted review, or -1 if an error occurred.
     */
    public long addMovieReview(String movieName, int year, float points, String reviewText) {
        SQLiteDatabase db = this.getWritableDatabase(); // Open database in write mode
        ContentValues values = new ContentValues(); // Object to store values

        // Put values into the ContentValues object
        values.put(COLUMN_MOVIE_NAME, movieName);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_POINTS, points);
        values.put(COLUMN_REVIEW_TEXT, reviewText);

        return db.insert(TABLE_REVIEWS, null, values); // Insert data into the database
    }

    /**
     * Retrieves a list of all unique movie names from the database.
     *
     * @return A list of movie names.
     */
    public List<String> getAllMovieNames() {
        List<String> movieNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); // Open database in read mode

        // Query to retrieve all movie names
        Cursor cursor = db.query(
                TABLE_REVIEWS,
                new String[]{COLUMN_MOVIE_NAME}, // Select only the movie name column
                null, null,
                COLUMN_MOVIE_NAME, null, null // Group by movie name to get unique values
        );

        // Iterate through the results and add to the list
        if (cursor.moveToFirst()) {
            do {
                movieNames.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_NAME)));
            } while (cursor.moveToNext());
        }

        cursor.close(); // Close the cursor
        return movieNames;
    }

    /**
     * Retrieves detailed review information for a specific movie.
     *
     * @param movieName The name of the movie.
     * @return A MovieReview object containing the details, or null if not found.
     */
    public MovieReview getMovieReviewDetails(String movieName) {
        SQLiteDatabase db = this.getReadableDatabase(); // Open database in read mode

        // Query to get details for a specific movie
        Cursor cursor = db.query(
                TABLE_REVIEWS,
                null, // Select all columns
                COLUMN_MOVIE_NAME + " = ?",
                new String[]{movieName}, // Condition to filter by movie name
                null, null, null
        );

        MovieReview review = null; // Initialize review object

        // If a review exists, populate the MovieReview object
        if (cursor.moveToFirst()) {
            review = new MovieReview(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOVIE_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_YEAR)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_POINTS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REVIEW_TEXT))
            );
        }

        cursor.close(); // Close the cursor
        return review;
    }

    /**
     * Inner class to represent a Movie Review.
     */
    public static class MovieReview {
        public String movieName;
        public int year;
        public float points;
        public String reviewText;

        /**
         * Constructor to initialize a MovieReview object.
         *
         * @param movieName  The movie's name.
         * @param year       The year of release.
         * @param points     The rating points.
         * @param reviewText The review text.
         */
        public MovieReview(String movieName, int year, float points, String reviewText) {
            this.movieName = movieName;
            this.year = year;
            this.points = points;
            this.reviewText = reviewText;
        }
    }
}
