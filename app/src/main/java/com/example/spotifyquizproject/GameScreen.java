package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

public class GameScreen extends AppCompatActivity {

    private boolean isPaused = false;
    private int P1Points = 0;
    private int P2Points = 0;
    public static String imageUri;
    public static String name;
    public static String artist;

    public static ImageView trackCover;

    protected void onEvent(PlayerState playerState) {
        if(playerState.track != null) {
            MainActivity.spotifyHelper.getmSpotifyAppRemote()
                    .getImagesApi()
                    .getImage(playerState.track.imageUri)
                    .setResultCallback(
                            bitmap -> {
                                trackCover = findViewById(R.id.songReveal);
                                Log.d("rus", "Image Data: " + trackCover);
                            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Bundle bundle = getIntent().getExtras();

        onEvent();
        
        if(RevealScreen.count != 0){
            connected(PlayQuiz.text);
            P1Points = bundle.getInt("P1Points");
            P2Points = bundle.getInt("P2Points");
        }
        TextView p1 = findViewById(R.id.points1);
        p1.setText(Integer.toString(P1Points));

        TextView p2 = findViewById(R.id.points2);
        p2.setText(Integer.toString(P2Points));


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

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(MainActivity.spotifyHelper.getmSpotifyAppRemote());
    }

    public void switchToReveal(View view){
        TextView textView1 = findViewById(R.id.points1);
        MainActivity.spotifyHelper.setP1Points(Integer.parseInt(textView1.getText().toString()));
        TextView textView2 = findViewById(R.id.points2);
        MainActivity.spotifyHelper.setP2Points(Integer.parseInt(textView2.getText().toString()));

        Intent intent = new Intent(this, RevealScreen.class);
        startActivity(intent);
    }



    private void connected(String uri) {
        // Play a playlist
        MainActivity.spotifyHelper.getmSpotifyAppRemote().getPlayerApi().setShuffle(true);
        MainActivity.spotifyHelper.getmSpotifyAppRemote().getPlayerApi().play(uri);
        
        MainActivity.spotifyHelper.getmSpotifyAppRemote().getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name);
                        imageUri = ("https://i.scdn.co/image/"+ track.imageUri.toString().substring(22, track.imageUri.toString().length()-2));
                        name = track.name;
                        artist = track.artist.name;
                    }
                });
    }

    public void playPlaylist(View view){
        connected(PlayQuiz.text);
    }

    public void pause(View view){
        if (!isPaused){
            MainActivity.spotifyHelper.getmSpotifyAppRemote().getPlayerApi().pause();
            isPaused = true;
        } else {
            MainActivity.spotifyHelper.getmSpotifyAppRemote().getPlayerApi().resume();
            isPaused = false;
        }
    }

    public void skipSong(View view){
        connected(PlayQuiz.text);
    }


    public static String compareValues(int playerOnePoints, int playerTwoPoints) {
        if(playerOnePoints > playerTwoPoints) {
            return "Player One";
        }
        else if(playerOnePoints == playerTwoPoints) {
            return "Tie";
        }
        else {
            return "Player Two";
        }
    }

    public void switchToEnd(View view) {
        MainActivity.spotifyHelper.getmSpotifyAppRemote().getPlayerApi().pause();
        MainActivity.spotifyHelper.getmSpotifyAppRemote().disconnect(MainActivity.spotifyHelper.getmSpotifyAppRemote());
        Intent intent = new Intent(this, EndScreen.class);
        intent.putExtra(Integer.toString(MainActivity.spotifyHelper.getP1Points()), (Integer.toString(MainActivity.spotifyHelper.getP2Points())));
        startActivity(intent);
    }
}