package com.example.android.java.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

// By centralizing common bits of functionality in this way in Method libraries,
// it makes it easy to change common behavior throughout the app by making
// small changes in one Java sourcecode file
public class ActivityHelper {

    public static void log(Context context, TextView tv, String message, boolean append) {

        // If the TextView and Message aren't null, and the Message isn't blank...
        if (tv != null & message != null && message.length() > 0) {

            if (append) { // If the append value is true

                // Take the message and add a line feed and append
                // the value to the existing display of the TextView object
                tv.append(message + "\n");

            } else { // If the append value is false

                // Replace the previous text in the TextView
                // with the new message
                tv.setText(message);
            }

            // Display the message in the logcat.
            Log.i(context.getClass().getSimpleName(), message);

            // Display the message via Toast
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
