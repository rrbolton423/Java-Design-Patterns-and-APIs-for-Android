package com.example.android.java;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.java.utilities.ActivityHelper;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements TextToSpeech.OnInitListener {

    private ScrollView mScroll;
    private TextView mLog, mEditText;

    // Declare a TextToSpeech object
    private TextToSpeech tts;

    // Declare a boolean to track whether tts is initialized
    private boolean ttsInitialized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

        mEditText = (TextView) findViewById(R.id.searchText);

        // Initialize the TextToSpeech object, passing the context,
        // and a TextToSpeech.OnInitListener which is implemented
        // by the MainActivity class
        tts = new TextToSpeech(getApplicationContext(), this);

    }

    public void onRunBtnClick(View v) {

        // Get the text entered in the TextView
        String text = mEditText.getText().toString();

        // If there was no text entered...
        if (text.length() == 0) {

            // Display helpful Toast
            Toast.makeText(MainActivity.this, "What do you want to say?",
                    Toast.LENGTH_SHORT).show();

            // If there was text entered...
        } else {

            // Display the text
            displayMessage(text);

            // Say the text out loud
            saySomething(text);
        }
    }

    public void onClearBtnClick(View v) {
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void displayMessage(String message) {
        ActivityHelper.log(this, mLog, message, true);
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    // This method is listening for the event to occur when the
    // TextToSpeech object is initialized
    @Override
    public void onInit(int status) {

        // If the tts object was initialized successfully...
        if (status == TextToSpeech.SUCCESS) {

            // Set the language to US
            int result = tts.setLanguage(Locale.US);

            // If the result of setting the language to US is missing
            // language data or the language is not supported...
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {

                // Display language not supported message
                displayMessage("This language is not supported");

                // If the result of setting the language to US isn't missing
                // language data and the language is supported...
            } else {

                // TTS object is usable
                ttsInitialized = true;
            }

            // If the tts object was not initialized successfully...
        } else {

            // Display initialization failed message
            displayMessage("TTS initialization failed");
        }
    }

    // This method says something out loud
    private void saySomething(String speech) {

        // If the TextToSpeech object is not initialized
        if (!ttsInitialized) {

            // Display TextToSpeech wasn't initialized message
            displayMessage("TextToSpeech wasn't initialized");

            // Return from the method
            return;
        }

        // Make the TextToSpeech object speak by passing the speech,
        // the type of queue, a null bundle, and an id to refer to
        // this TextToSpeech object later if needed.
        tts.speak(speech,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "speech");
    }
}