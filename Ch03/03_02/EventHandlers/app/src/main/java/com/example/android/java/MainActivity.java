package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private TextView mLog;

    // Define Button fields
    private Button mButtonRun, mButtonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

        // Initialize Buttons
        mButtonRun = (Button) findViewById(R.id.btnRun);
        mButtonClear = (Button) findViewById(R.id.btnClear);

        // Create an anonymous implementation of the OnClickListener interface
        mButtonRun.setOnClickListener(new View.OnClickListener() {

            // Implement interface's callback method
            @Override
            public void onClick(View view) {

                // Display the message
                displayMessage("Running code!");
            }
        });

        // Create another anonymous implementation of the OnClickListener interface
        mButtonClear.setOnClickListener(new View.OnClickListener() {

            // Implement interface's callback method
            @Override
            public void onClick(View view) {

                // Clear the output
                mLog.setText("");
                mScroll.scrollTo(0, mScroll.getBottom());            }
        });
    }

//    public void onRunBtnClick(View v) { }
//
//    public void onClearBtnClick(View v) { }

    public void displayMessage(String message) {
        mLog.append(message + "\n");
        mScroll.scrollTo(0, mScroll.getBottom());
    }
}