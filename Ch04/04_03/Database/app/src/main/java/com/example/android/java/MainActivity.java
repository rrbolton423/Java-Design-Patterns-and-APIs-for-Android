package com.example.android.java;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.java.database.MyDataSource;
import com.example.android.java.utilities.ActivityHelper;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private TextView mLog, mSearch;

    // Define MyDataSource class
    private MyDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

        mSearch = (TextView) findViewById(R.id.searchText);

        // Instantiate the DataSource
        dataSource = new MyDataSource(this);

        // Initialize the data in the database if there isn't
        // any data already
        dataSource.initData();

    }

    public void onRunBtnClick(View v) {

        // Search the database when the button is clicked
        searchDatabase(v);
    }

    public void onClearBtnClick(View v) {
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void displayMessage(String message) {
//        mLog.append(message + "\n");
        ActivityHelper.log(this, mLog, message, true);
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void searchDatabase(View view) {

        // Take the value entered into the Search box
        String searchString = mSearch.getText().toString();

        // Make sure the user has typed something in
        if (searchString.length() == 0) {

            // Display insert data Toast
            Toast.makeText(MainActivity.this, "Enter an integer between 1 and 100",
                    Toast.LENGTH_SHORT).show();

            // Return from the method
            return;
        }

        // Transform the value into an integer
        int indexValue = new Integer(searchString);

        // Call the selectRecord method, passing the primary key
        // to filter on, and receive a cursor object
        Cursor cursor = dataSource.selectRecord(indexValue);

        // Loop through the cursor's data
        // If moveToNext() returns true, data has been found
        if (cursor.moveToNext()) {

            // Get the name of a person from the cursor object
            String name = cursor.getString(cursor.getColumnIndex(MyDataSource.PERSON_NAME));

            // Display found Toast
            Toast.makeText(MainActivity.this, "You found " + name, Toast.LENGTH_SHORT).show();

        } else { // If moveToNext() returns false, no data has been found

            // Display not found Toast
            Toast.makeText(MainActivity.this, "Person not found", Toast.LENGTH_SHORT).show();
        }
    }
}