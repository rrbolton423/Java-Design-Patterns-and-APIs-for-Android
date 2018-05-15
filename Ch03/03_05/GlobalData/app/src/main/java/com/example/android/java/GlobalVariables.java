package com.example.android.java;

import android.app.Application;

// The Android Application Framework gives you a way to store
// application data in memory globally using the Application
// Context.
public class GlobalVariables extends Application {

    // Create String data field
    private String textToDisplay;

    // Create getter for the String data field
    public String getTextToDisplay() {
        return textToDisplay;
    }

    // Create setter for the String data field
    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }
}
