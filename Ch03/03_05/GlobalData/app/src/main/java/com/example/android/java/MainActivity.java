package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private TextView mLog;

    // Declare a reference ot the GlobalVariables class
    private GlobalVariables appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);

        // Initialize the GlobalVariables object
        appContext = (GlobalVariables) getApplicationContext();

        // Get the value from the GlobalVariables class and display it
        // as the Activity starts up
        mLog.setText(appContext.getTextToDisplay());
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
    protected void onDestroy() {
        super.onDestroy();

        // Log info
        ActivityHelper.log(this, mLog, "onDestroy", true);

        // Save the current state of the TextView
        // into the Application context
        appContext.setTextToDisplay(mLog.getText().toString());
    }
}