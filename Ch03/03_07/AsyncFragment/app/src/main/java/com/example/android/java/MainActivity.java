package com.example.android.java;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements AsyncFragment.ParentActivity {

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private ScrollView mScroll;
    private TextView mLog;

    // Create an instance of my Fragment class
    private AsyncFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

        // Get a reference to the FragmentManager
        FragmentManager manager = getFragmentManager();

        // Get a reference to the AsyncFragment
        mFragment = (AsyncFragment) manager.findFragmentByTag(FRAGMENT_TAG);

        // Check if the Fragment is null
        if (mFragment == null) {

            // Initialize the Fragment
            mFragment = new AsyncFragment();

            // Add the Fragment to the Activity
            manager.beginTransaction().add(mFragment, FRAGMENT_TAG).commit();

        }
    }

    public void onRunBtnClick(View v) {

        // Have the Fragment execute it's AsyncTask, passing 2 String values
        mFragment.runAsyncTask("Chocolate", "Vanilla", "Strawberry");
    }

    public void onClearBtnClick(View v) {
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void displayMessage(String message) {
        mLog.append(message + "\n");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    @Override
    public void handleTaskUpdate(String message) {

        // Display the message that is received from the AsyncFragment
        displayMessage(message);
    }
}