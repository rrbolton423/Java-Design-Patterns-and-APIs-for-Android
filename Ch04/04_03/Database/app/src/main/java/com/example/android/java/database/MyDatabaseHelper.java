package com.example.android.java.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// The purpose of a database helper class is to define
// the structure of the database, and then to initialize the
// database if it doesn't already exist on disk

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // DB name and version constants
    private static final String DATABASE_NAME = "people";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + MyDataSource.PEOPLE_TABLE + " ("
                    + MyDataSource.PERSON_ID + " INTEGER PRIMARY KEY, "
                    + MyDataSource.PERSON_NAME + " TEXT NOT NULL);";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + MyDataSource.PEOPLE_TABLE);
        onCreate(database);
    }
}
