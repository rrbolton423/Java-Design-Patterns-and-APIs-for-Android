package com.example.android.java;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;

// By implementing SensorEventListener, I'm setting up my MainActivity
// class as a listener for the accelerometer sensor.
public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    private ScrollView mScroll;
    private TextView mLog;

    /*
    Register the sensor and then keep track of the current orientation
    of the device
     */

    // Declare SensorManager object
    private SensorManager sensorManager;

    // Declare Sensor object
    private Sensor accelerometer;

    /*
    Add a few fields to keep track of the current state of the device
     */

    // Keeps track of the moment in time when the device was last shaken
    private long lastUpdate = 0;

    //
    private float lastX, lastY, lastZ;

    // Will determine how much the device has to be moved before I
    // consider it to be a shaking action
    private static final int SHAKE_THRESHOLD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

        // Initialize the SensorManager object
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Initialize the Accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register the listener
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // un-Register the listener
        sensorManager.unregisterListener(this);
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

    /*
     Implement the code that reacts when the user shakes the device
     in onSensorChanged()
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        // When onSensorChanged is called, it receives an event
        // object that has a sensor property.

        // Save the reference to thar sensor
        Sensor sensor = event.sensor;

        // If the sensor is a accelerometer...
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // The event object has three values, which are the X, Y,
            // and Z orientation of the device. It's a simple array.
            // I'm storing those as the current X, Y, and Z values
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Get the current time as a long value
            long currentTime = System.currentTimeMillis();

            // This conditional block calculates the currentTime against
            // the lastUpdate and makes sure that at least 300 milliseconds
            // have passed
            if ((currentTime - lastUpdate) > 300) {

                // Calculate the difference of the lastUpdate
                // from the currentTime
                long diffTime = (currentTime - lastUpdate);

                // Save the current time as the lastUpdate
                lastUpdate = currentTime;

                // Calculate the speed of the difference
                float speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000;

                // If the result exceeds my shake threshold...
                if (speed > SHAKE_THRESHOLD) {

                    // Display shake message
                    displayMessage("Stop shaking me!");
                }

                // Save the X, Y, and Z values as the last
                // registered values.
                lastX = x;
                lastY = y;
                lastZ = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // I don't need any code in onAccuracyChanged(), I
        // just need the method stub there to satisfy the interface.
    }
}