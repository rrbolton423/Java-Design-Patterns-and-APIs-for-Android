package com.example.android.java.utilities;

import com.example.android.java.Product;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MyPullParser {

    // Field for using logcat
    private static final String LOGTAG = "PULL_PARSER";

    // Constants for names of tags in the XML file
    private static final String NAME_TAG = "name";
    private static final String PRICE_TAG = "price";

    // Declare a Product object
    private Product mProduct = null;

    // Declare a list holding Product objects
    List<Product> mProducts = new ArrayList<>();

    // Declare a TAG to track where I am at in the XML
    // file while parsing
    private String mTag = null;

    // This method receives an (assumed) XML formatted String,
    // and will return a list of Products
    public List<Product> parseXML(String xml) {

        try { // Try to...

            // Create a Factory object
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            // Set the nameSpaceAware to true
            factory.setNamespaceAware(true);

            // Create the Parser object
            XmlPullParser xpp = factory.newPullParser();

            // Create a StringReader object an pass in the String XML file
            StringReader reader = new StringReader(xml);

            // Set the input source for the parser to the given reader
            xpp.setInput(reader);

            // Get the event type of the XML Document
            int eventType = xpp.getEventType();

            // Loop until the end of the XML Document
            while (eventType != XmlPullParser.END_DOCUMENT) {

                // If the event type is a start tag...
                if (eventType == XmlPullParser.START_TAG) {

                    // Get the name of the Start Tag and
                    // pass it to the method that handles start tags
                    handleStartTag(xpp.getName());

                    // If the event type is a end tag...
                } else if (eventType == XmlPullParser.END_TAG) {

                    // Reset the Tag String to null
                    mTag = null;

                    // If the event type is a text tag...
                } else if (eventType == XmlPullParser.TEXT) {

                    // Get the text of the current tag and
                    // pass it to the method that handles text tags
                    handleText(xpp.getText());
                }

                // Go to the next event type
                eventType = xpp.next();
            }

        } catch (Exception e) { // Exception handle

            // Print the stack trace
            e.printStackTrace();
        }

        // Return the list of products
        return mProducts;
    }

    // Method handles text nodes
    private void handleText(String text) {

        // Get the name of the tag
        String xmlText = text;

        // If the product object and the tag name
        // isn't null...
        if (mProduct != null && mTag != null) {

            // If the tag name is a "name" tag...
            if (mTag.equals(NAME_TAG)) {

                // Set the name of the current Product object
                // to the name text
                mProduct.setName(xmlText);

                // If the tag name is a "price" tag...
            } else if (mTag.equals(PRICE_TAG)) {

                // Get the double value of the price
                // that was a String
                double price = Double.parseDouble(xmlText);

                // Set the price of the current Product object
                // to the price text
                mProduct.setPrice(price);
            }
        }
    }

    // Method handles start nodes
    private void handleStartTag(String name) {

        // If the name of the start tag is "product"...
        if (name.equals("product")) {

            // Create a new Product object
            mProduct = new Product();

            // Add that product to the product list
            mProducts.add(mProduct);

        } else { // If the name of the start tag isn't "product"...

            // Save the name of the tag to a field,
            // which will be used when looking at the text values
            mTag = name;
        }
    }

}
