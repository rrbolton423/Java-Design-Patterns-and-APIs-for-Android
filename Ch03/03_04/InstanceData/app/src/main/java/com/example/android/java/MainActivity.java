package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;

public class MainActivity extends AppCompatActivity {

    // Create Key to refer to the save data
    private static final String LOG_TEXT_KEY = "LOG_TEXT_KEY";

    private ScrollView mScroll;
    private TextView mLog;

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
        displayMessage("Running code!");
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // Log save info
        ActivityHelper.log(this, mLog, "saving state", true);

        // Save the TextView's values to the outState
        outState.putString(LOG_TEXT_KEY, mLog.getText().toString());

        // Call to super class saves data to the outState
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        // Call to super retrieves the data from the framework
        super.onRestoreInstanceState(savedInstanceState);

        // Restore the TextView's values from the savedInstanceState
        mLog.setText(savedInstanceState.getString(LOG_TEXT_KEY));

        // Log restore info
        ActivityHelper.log(this, mLog, "restoring state", true);
    }
}