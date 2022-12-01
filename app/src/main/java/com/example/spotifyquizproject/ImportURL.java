package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

public class ImportURL extends AppCompatActivity {


    public static String spotURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importurl);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder("0b6a257c37744cfabe83c6949f68019f")
                        .setRedirectUri("http://localhost:8888/callback")
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        MainActivity.spotifyHelper.setmSpotifyAppRemote(spotifyAppRemote);
                        Log.d("MainActivity", "Connected! Yay!");

                        // Now you can start interacting with App Remote
                        //connected();

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MyActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

//    @Override
////    protected void onStop() {
////        super.onStop();
////        SpotifyAppRemote.disconnect(MainActivity.spotifyHelper.getmSpotifyAppRemote());
////    }

    public void switchScreens1(View view){
        EditText editText = findViewById(R.id.playlistLink);
        Log.d("Praneet", "switchScreens1: " + editText.getText().toString());
        if(editText.getText().toString().equals("")) {
            toastClicked();
        }

        else {
            String text = editText.getText().toString();
            String playlist = "playlist";

            int index = text.indexOf("playlist");
            int a = playlist.length() + index + 1;
            //Changed that from string url to spotURL
            spotURL = "spotify:playlist:" + text.substring(a);

            Intent intent = new Intent(this, PlayQuiz.class);
            //Changed that from string url to spotURL
            intent.putExtra("inputText", spotURL);
            startActivity(intent);
        }
    }

    public void toastClicked() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Input Spotify Playlist URL", Toast.LENGTH_SHORT);
        toast.show();
    }
}