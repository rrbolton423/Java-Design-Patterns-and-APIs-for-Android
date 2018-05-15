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
    private List<Person> mPeople = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        displayMessage("Before sorting");
        for (Person person : mPeople) {
            displayMessage(person.toString());
        }

        // Create an instance of the Person class, and get the first
        // item from the list
        Person p = mPeople.get(0);

        // Static methods can be called directly from the interface itself,
        // whereas default methods can be called from any instance of the
        // class that implements the interface. The result can be a
        // very clean and readable code that's very easy to maintain
        // and reverse engineer
        mPeople.sort(p::compareByAge);

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