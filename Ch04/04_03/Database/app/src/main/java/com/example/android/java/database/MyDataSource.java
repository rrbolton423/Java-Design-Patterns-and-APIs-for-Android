package com.example.android.java.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDataSource {

    // Declare DBHelper
    private MyDatabaseHelper dbHelper;

    // Declare SQLiteDatabase
    private SQLiteDatabase database;

    // Create DB table and column names
    public final static String PEOPLE_TABLE = "people"; // name of table
    public final static String PERSON_ID = "personId"; // id value for employee
    public final static String PERSON_NAME = "personName";  // name of employee

    public MyDataSource(Context context) {

        // Instantiate the DBHelper class
        dbHelper = new MyDatabaseHelper(context);

        // Get an instance of the SQLiteDatabase, calling the Helper's onCreate() method
        database = dbHelper.getWritableDatabase();
    }

    public long createRecord(int id, String name) {

        // Create a ContentValues object
        ContentValues values = new ContentValues();

        // Put the id of he person into the PERSON_ID table
        values.put(PERSON_ID, id);

        // Put the name of he person into the PERSON_TABLE table
        values.put(PERSON_NAME, name);

        // Insert the values into the database and return how many rows were created
        // during the insertion
        return database.insert(PEOPLE_TABLE, null, values);
    }

    public Cursor selectRecord(int personId) {

        // Create a String array of the columns in the database
        String[] cols = new String[]{PERSON_ID, PERSON_NAME};

        // Query the database, passing the table, the columns to query,
        // and a filter specifying the row in the database to search.
        // A cursor object is returned
        return database.query(true, PEOPLE_TABLE, cols,
                MyDataSource.PERSON_ID + "=" + personId,
                null, null, null, null, null);
    }

    public void initData() {

        // Get the number of rows in the database table
        long count = DatabaseUtils.queryNumEntries(database, PEOPLE_TABLE);

        // If the number of rows in the database are 0...
        if (count == 0) {

            // Log init message
            Log.i("MyDataSource", "Initializing data");

            // For 100 times
            for (int i = 1; i <= 100; i++) {

                // Add a record to the database
                createRecord(i, "Person " + i);
            }

        } else { // If there is already data / rows in the database

            // Log helpful data message
            Log.i("MyDataSource", "Data already initialized with " + count + " rows");
        }
    }
}


