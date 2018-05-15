package com.example.android.java;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/*
Instead of hosting an AsyncTask in an Activity,
create a Fragment and host the task there. A Fragment
object can be retained in memory during a configuration change.
And it can keep that task running even when the Activity is
destroyed and recreated.
*/

// This class will host an AsyncTask, the user won't see
// the Fragment itself
public class AsyncFragment extends Fragment {

    // Declare an instance of the Interface as a member field
    private ParentActivity mParent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To make the Fragment stay in memory, call the
        // setRetainInstance() method
        setRetainInstance(true);
    }

    // Define an interface
    public interface ParentActivity {

        // The purpose of this method is to send a String value
        // back to the parent Activity when a Task is running in
        // the fragment
        void handleTaskUpdate(String message);
    }

    // Override
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Initialize the interface instance
        // Attach the Fragment to the Activity
        mParent = (ParentActivity) context;

        // Log "attached"
        Log.i("AsyncFragment", "attached");
    }

    public void runAsyncTask(String ... params) {

        // Create an instance of the AsyncTask
        MyTask task = new MyTask();

        // Execute the Task passing 3 String values
        task.execute(params);
    }

    class MyTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {

            // Loop through the values
            for (String s : params) {

                // Display the values on the main thread
                // one by one
                publishProgress("I got " + s);

                // Delay for 1 second in between
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            // Display the String value(s)
            mParent.handleTaskUpdate(values[0]);
        }
    }
}