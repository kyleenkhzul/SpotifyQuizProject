package com.example.spotifyquizproject;
// https://developer.spotify.com/documentation/android/quick-start/
// ^ tutorial for implementing spotify

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static SpotifyHelper spotifyHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spotifyHelper = new SpotifyHelper();
    }

    public void changeToUrl(View view){
        // get a reference to the EditText element in the xml page that is connected to this activity.
        // to this activity
        // extract the text
        /*
        Create an intent (envelope) by telling Android who the sender and receiver is
        then we need to fill teh intent and lastly we need to start the intent.
         */
        Intent intent = new Intent(this, ImportURL.class);
        startActivity(intent);
    }
}