package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;
import com.example.android.java.utilities.FileHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        // Read the json file from disk
        String json = FileHelper.readFileFromAssets(this, "data.json");

        // Display the JSON file's contents
//        mLog.setText(json);

        try { // Try to...

            // Create an instance of the class JSONArray
            // This class creates a JSONArray with values from the
            // JSON String
            JSONArray jsonArray = new JSONArray(json);

            // Display the items in the JSONArray
            displayMessage("There are " + jsonArray.length() + " items");

            // Loop through the JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {

                // Create an instance of the JSONObject class, which will
                // be used to represent each item in the array
                JSONObject item = (JSONObject) jsonArray.get(i);

                /*
                Extract 2 values from each JSONObject, the price and the name
                */
                String itemName = item.getString("name");
                double itemPrice = item.getDouble("price");

                // Format the data using a StringBuilder for display
                StringBuilder builder = new StringBuilder(itemName)
                        .append(" (")
                        .append(itemPrice)
                        .append(")\n");

                // Display the String version of the JSON file
                // in the TextView of the MainActivity
                mLog.append(builder.toString());
            }

        } catch (JSONException e) { // JSONException Handle

            // Print the stack trace
            e.printStackTrace();
        }
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
}