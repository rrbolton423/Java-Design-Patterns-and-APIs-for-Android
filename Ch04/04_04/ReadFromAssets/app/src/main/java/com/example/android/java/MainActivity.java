package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

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

        // Get the String value of the file from the Assets directory
        String text = readFileFromAssets("lorem_ipsum.txt");

        // If there was text returned from the readFileFromAssets() method...
        if (text != null) {

            // Replace any existing text in the TextView
            // with the contents of the file
            mLog.setText(text);
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

    // This method receives a file name as a String to
    // read and return as a String value
    private String readFileFromAssets(String fileName) {

        // Declare BufferedReader objects
        BufferedReader reader = null;

        // Declare StringBuilder object
        StringBuilder builder = new StringBuilder();

        try { // Try to...

            // Instantiate the BufferedReader object, with the
            // InputStreamReader, that accepts an input stream from
            // the assets directory; being the actual txt file
            reader = new BufferedReader(new InputStreamReader(
                    getAssets().open(fileName)
            ));

            // Create a String to read the file one line at a time
            String line;

            // Read the file
            while ((line = reader.readLine()) != null) {

                // Append the read data line by line and
                // add a line feed at the end of each line
                builder.append(line).append("\n");
            }

            // Return the String version of the data
            return builder.toString();

        } catch (FileNotFoundException e) { // Catch the FileNotFoundException

            // Display the exception
            displayMessage("File not found: " + e.getMessage());

        } catch (Exception e) { // Catch the Exception

            // Display the exception
            displayMessage(e.getMessage());

        } finally { // Finally

            // Make sure the Reader isn't null...
            if (reader != null) {

                try { // Try to...

                    // Close the Reader object
                    reader.close();

                } catch (Exception e) { // Catch the Exception

                    // Display the Exception
                    displayMessage(e.getMessage());
                }
            }
        }

        // Return null
        return null;
    }
}