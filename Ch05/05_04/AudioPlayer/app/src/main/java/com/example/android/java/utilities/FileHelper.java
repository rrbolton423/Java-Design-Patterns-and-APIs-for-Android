package com.example.android.java.utilities;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
The purpose of this file is to allow you to easily take a file from
the assets folder and copy it to the app's cache. This is because you
can't play a music file directly from assets. It has to be on the local
file system outside of the application package. The cache is a good place
to do it because you can copy it there, and then later on if the file
is still available you don't have to copy it again.
 */
public class FileHelper {

    public static String copyAssetToCache(Context context, String fileName) {

        // Create the file path name with the cache prefix
        String cachedFileName = context.getCacheDir() + fileName;

        // Create the File object
        File cachedFile = new File(cachedFileName);

        // Declare and initialize a FileOutputStream to null
        FileOutputStream fos = null;

//      If the file doesn't exist in cache, copy it from assets
        if (!cachedFile.exists()) {

            try { // Try to...

                // Create an InputStream from the file in the Assets directory
                InputStream inputStream = context.getAssets().open(fileName);

                // Get the size of the InputStream
                int size = inputStream.available();

                // Create a buffer with the size of the stream
                byte[] buffer = new byte[size];

                // Read the InputStream into the buffer
                int ignore = inputStream.read(buffer);

                // Close the InputStream
                inputStream.close();

                // Instantiate the FileOutputStream, passing the
                // cachedFile
                fos = new FileOutputStream(cachedFile);

                // Write the buffer data to the FileOutputStream
                fos.write(buffer);

                // Close the FileOutputStream
                fos.close();

            } catch (Exception e) { // Exception handle

                // Throw a RuntimeException
                throw new RuntimeException(e);

            } finally { // Finally...

                // If the FileOutputStream is not null...
                if (fos != null) {

                    try { // Try to...

                        // Close the FileOutputStream
                        fos.close();

                    } catch (IOException e) { // IOException handle

                        // Print the stack trace
                        e.printStackTrace();
                    }
                }
            }

        }

        // Return the cached file name whether it already exists,
        // or if it was created for the first time
        return cachedFileName;
    }
}
