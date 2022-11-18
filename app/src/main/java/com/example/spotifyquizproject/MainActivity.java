package com.example.spotifyquizproject;
// https://developer.spotify.com/documentation/android/quick-start/
// ^ tutorial for implementing spotify

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;

import com.spotify.protocol.types.Track;

public class MainActivity extends AppCompatActivity {
    private static final String CLIENT_ID = "0b6a257c37744cfabe83c6949f68019f";
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private SpotifyAppRemote mSpotifyAppRemote;
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

//
}