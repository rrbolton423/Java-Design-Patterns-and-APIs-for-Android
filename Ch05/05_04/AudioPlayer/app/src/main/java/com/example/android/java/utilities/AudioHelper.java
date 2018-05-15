package com.example.android.java.utilities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

public class AudioHelper {

    // Field to store the current context
    private Context context;

    // Field to store a String representing the audio file name
    private String audioFile;

    // Instance of the MediaPlayer class
    private MediaPlayer mediaPlayer;

    // Constructor to store the context and the audio file name
    public AudioHelper(Context context, String audioFile) {
        this.context = context;
        this.audioFile = audioFile;
    }

    public void prepareAndPlay() {

        // Move the file to a place which you can play the file (cache)
        String fullFileName =
                FileHelper.copyAssetToCache(context, audioFile);

        // Create an instance of the MediaPlayer
        mediaPlayer = new MediaPlayer();

        // Set up a listener so that when the MediaPlayer opens up the file,
        // it has to tell you when it's ready to play it
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("AudioHelper", "Prepared and playing!");

                // Play the audio file once the MediaPlayer is prepared to play it
                play();
            }
        });

        // Use a file input stream to open the file from persistent storage
        FileInputStream stream = null;

        try { // Try to...

            // Instantiate the FileInputStream with the file name
            stream = new FileInputStream(fullFileName);

            // Set the data source of the media player to that stream
            mediaPlayer.setDataSource(stream.getFD());

            // Set a stream type saying that I'm streaming "MUSIC"
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            // Call the prepare() method which leads to the onPrepared()
            // method being called within the OnPreparedListener of the MediaPlayer
            mediaPlayer.prepare();


        } catch (IOException e) { // IOException handle

            // Log Exception message
            Log.e("AudioHelper", "Error: " + e.getMessage());

        } finally { // Finally...

            // If the stream isn't null...
            if (stream != null) {

                try { // Try to...

                    // Close the FileInputStream
                    stream.close();

                    // IOException handle
                } catch (IOException ignore) {
                }
            }
        }
    }

    public void play() {

        // Start the media, allowing the music to play
        mediaPlayer.start();
    }

    //  Stop this cue and set its player object to null
    public void stop() {

        // If the mediaPlayer isn't null...
        if (mediaPlayer != null) {

            // Stop playback of this MediaPlayer object
            mediaPlayer.stop();

            // Release the resources associated with this
            // MediaPlayer object
            mediaPlayer.release();

            // Reset the mediaPlayer to "null", so it can be
            // re-instantiated and used again
            mediaPlayer = null;
        }
    }

}
