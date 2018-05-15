package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/*
SparseArrays map integers to Objects.
Unlike a normal array of Objects, there can be gaps in the indices.
It is intended to be more memory efficient than using a HashMap
to map Integers to Objects, both because it avoids auto-boxing keys
and its data structure doesn't rely on an extra entry object for each mapping.
 */

public class MainActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private TextView mLog;

//    private Map<Integer, Person> map;

    // Define the SparseArray
    private SparseArray<Person> sparseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

    }

    public void onRunBtnClick(View v) {

//        map = new HashMap<>();

        // Initialize the SparseArray
        sparseArray = new SparseArray<>();

        // Loop 1000 times
        for (int i = 0; i < 10000; i++) {

            // Create a Person object
            Person p = new Person(i, "Person " + i);
//            map.put(i, p);

            // Add each object to the Sparse array, using
            // an integer as a key
            sparseArray.append(i, p);
        }

        // Display the amount of objects in the SparseArray
        displayMessage("There are " + sparseArray.size() + " Person objects");
//        displayMessage("There are " + sparseArray.size() + " Person objects");
    }

    public void onClearBtnClick(View v) {
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void displayMessage(String message) {
        mLog.append(message + "\n");
//        ActivityHelper.log(this, mLog, message, true);
        mScroll.scrollTo(0, mScroll.getBottom());
    }
}