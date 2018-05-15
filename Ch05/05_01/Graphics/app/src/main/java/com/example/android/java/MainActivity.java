package com.example.android.java;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare a FrameLayout
    FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the FrameLayout
        mContainer = (FrameLayout) findViewById(R.id.frameLayout);
    }

    public void onRunBtnClick(View v) {

        // Create an instance of the CircleView object
        View circle = new CircleView(this, Color.CYAN);

        // Add the CircleView to the screen / container
        mContainer.addView(circle);
    }

    public void displayMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    // Have the CircleView class extend "View"
    class CircleView extends View {

        // Declare a color field
        private int color;

        // Generate a constructor that just receives
        // the current context, and a color int
        public CircleView(Context context, int color) {
            super(context);

            // Save the color to the field of the class
            this.color = color;
        }

        // Every View class has a method named onDraw() that can be
        // overwrote. When onDraw() is called, it passes in a reference
        // to a Canvas object. You can use that Canvas to draw whatever
        // you want.
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Get the width of the container View
            int x = getWidth();

            // Get the height of the container View
            int y = getHeight();

            // Set a radius for the circle I will draw
            int radius = 300;

            // Create an instance of the Paint class
            Paint paint = new Paint();

            /*
            Draw the Background
             */

            // Set the style to cover the entire container / background
            paint.setStyle(Paint.Style.FILL);

            // Set the color of the container / background
            paint.setColor(Color.GRAY);

            // Fill the entire canvas with the specified paint
            canvas.drawPaint(paint);

            /*
            Draw the Circle
             */

            // Set the color of the circle
            paint.setColor(color);

            // Draw the circle, passing the x and y coordinate,
            // radius, and paint object containing the color value
            canvas.drawCircle(x / 2, y / 2, radius, paint);

        }
    }

}