package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GroceryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "grocery_database";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_GROCERY_ITEMS = "grocery_items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";

    // Create table SQL
    private static final String CREATE_TABLE_GROCERY_ITEMS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_GROCERY_ITEMS + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT UNIQUE, "
                    + COLUMN_PRICE + " REAL)";

    public GroceryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GROCERY_ITEMS);
        Log.d("DB", "Database table created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERY_ITEMS);
        onCreate(db);
    }

    // Method to add a grocery item
    public void addGroceryItem(String name, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PRICE, price);
        db.insertWithOnConflict(TABLE_GROCERY_ITEMS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    // Method to get all grocery item names
    public List<String> getAllGroceryItemNames() {
        List<String> itemNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM " + TABLE_GROCERY_ITEMS, null);

        if (cursor.moveToFirst()) {
            do {
                itemNames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        } else {
            Log.d("DB", "No grocery items found.");
        }
        cursor.close();
        return itemNames;
    }

    // Method to get price of a specific item
    public double getItemPrice(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT price FROM " + TABLE_GROCERY_ITEMS + " WHERE name = ?", new String[]{itemName});

        double price = 0;
        if (cursor.moveToFirst()) {
            price = cursor.getDouble(0);
        }
        cursor.close();
        return price;
    }
}
