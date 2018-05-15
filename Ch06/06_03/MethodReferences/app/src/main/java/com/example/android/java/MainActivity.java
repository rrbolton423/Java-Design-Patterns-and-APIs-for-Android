package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private TextView mLog;

    // Declare a list of the People class
    private List<Person> mPeople = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add 3 instances of the People class to the list
        mPeople.add(new Person("Joe", 36));
        mPeople.add(new Person("Mary", 52));
        mPeople.add(new Person("Tom", 38));

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);

        findViewById(R.id.btnRun).setOnClickListener((View v) -> {
            Log.i("MainActivity", "onClick: " +
                    getResources().getResourceName(v.getId()));
            runCode();
        });

        findViewById(R.id.btnClear).setOnClickListener((View v) -> {
            Log.i("MainActivity", "onClick: " +
                    getResources().getResourceName(v.getId()));
            clearOutput();
        });

        mLog.setText("");
    }

    public void runCode() {

        // Loop through the list and display
        // each Person's information before sorting
        displayMessage("Before sorting");
        for (Person person : mPeople) {
            displayMessage(person.toString());
        }

        // Sort the list according to age using the sort() method
        // of the list.
        // I do this by calling the sort() method of the list interface,
        // and I all have to do is tell the compiler that I'm calling a method
        // from this class, "Person", then you pass in 2 colon characters "::",
        // which is the method reference operator, and I'll choose compareByAge().
        // I don't have to pass in the arguments here, because that work will be
        // done by the compiler.
        mPeople.sort(Person::compareByAge);

        // Loop through the list and display
        // each Person's information after sorting
        displayMessage("After sorting");
        for (Person person : mPeople) {
            displayMessage(person.toString());
        }

    }

    public void clearOutput() {
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void displayMessage(String message) {
        mLog.append(message + "\n");
        mScroll.scrollTo(0, mScroll.getBottom());
    }
}