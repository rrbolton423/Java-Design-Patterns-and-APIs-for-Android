package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;
import com.example.android.java.utilities.FileHelper;
import com.example.android.java.utilities.MyPullParser;

import java.util.List;

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
        String xml = FileHelper.readFileFromAssets(this, "data.xml");
//        mLog.setText(xml);

        /*
        Create an instance of MyPullParser class, and then call the
        method to parse the XML, and I'll receive back a list
        */

        // Create an instance of MyPullParser class
        MyPullParser parser = new MyPullParser();

        // Create a list that contains Product objects
        List<Product> products = parser.parseXML(xml);

        // Display the amount of Products in the list
        mLog.append("There are " + products.size() + " products\n");

        // Loop through the list of products
        for (Product product : products) {

            // Format the each instance of the data
            // using a StringBuilder object
            StringBuilder builder = new StringBuilder(product.getName())
                    .append(" (")
                    .append(product.getPrice())
                    .append(")\n");

            // Display the data in the Activity's TextView
            mLog.append(builder.toString());
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