package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;
import com.example.android.java.utilities.AudioHelper;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private TextView mLog;

    // Create an instance of the AudioHelper class
    private AudioHelper mAudioHelper;

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

        // Display generic message
        displayMessage("Running code!");

        // If the helper object is not null...
        if (mAudioHelper != null) {

            // Call the stop() method so that any existing music
            // that's playing can be stopped and the helper's
            // internal player object can be re-set to null
            mAudioHelper.stop();

        }

        // Instantiate the AudioHelper object, passing it the context
        // and the associated audio file name
        mAudioHelper = new AudioHelper(this, "musicFile.mp3");

        // Prepare and play the audio
        mAudioHelper.prepareAndPlay();

        // Display playing message
        displayMessage("Playing!");
    }

    public void onClearBtnClick(View v) {
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void displayMessage(String message) {
        ActivityHelper.log(this, mLog, message, true);
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void onStopBtnClick(View view) {

        // If the AudioHelper object isn't null...
        if (mAudioHelper != null) {

            // Stop the audio
            mAudioHelper.stop();

            // Display stopping message
            displayMessage("Stopping!");
        }

    }
}