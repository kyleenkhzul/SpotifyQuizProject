package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

public class GameScreen extends AppCompatActivity {

    private static final String CLIENT_ID = "0b6a257c37744cfabe83c6949f68019f";
    private static final String REDIRECT_URI = "http://localhost:8888/callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    private boolean isPaused = false;

    public static int playerOnePoints;
    public static int playerTwoPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Bundle bundle = getIntent().getExtras();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {
                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
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
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    public void switchToReveal(View view){
        TextView textView1 = findViewById(R.id.points1);
        playerOnePoints = Integer.parseInt(textView1.getText().toString());
        TextView textView2 = findViewById(R.id.points2);
        playerTwoPoints = Integer.parseInt(textView2.getText().toString());


        Intent intent = new Intent(this, RevealScreen.class);
        startActivity(intent);
    }

    public void switchToEnd(View view) {
        Intent intent = new Intent(this, EndScreen.class);
        intent.putExtra(Integer.toString(playerOnePoints), (Integer.toString(playerTwoPoints)));
        startActivity(intent);
    }

    private void connected(String uri) {
        // Play a playlist
        mSpotifyAppRemote.getPlayerApi().setShuffle(true);
        mSpotifyAppRemote.getPlayerApi().play(uri);
        
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name);
                    }
                });
    }

    public void playPlaylist(View view){
        connected(PlayQuiz.text);
    }

    public void pause(View view){
        if (!isPaused){
            mSpotifyAppRemote.getPlayerApi().pause();
            isPaused = true;
        } else {
            mSpotifyAppRemote.getPlayerApi().resume();
            isPaused = false;
        }
    }

    public void skipSong(View view){
        mSpotifyAppRemote.getPlayerApi().skipNext();
    }

    public void updatePointsOne(){
        TextView textView = findViewById(R.id.points1);
        textView.setText(Integer.toString(playerOnePoints));
    }

    public void updatePointsTwo(){
        TextView textView = findViewById(R.id.points2);
        textView.setText(Integer.toString(playerTwoPoints));
    }

    public static int getPlayerOnePoints() {
        return playerOnePoints;
    }

    public static int getPlayerTwoPoints() {
        return playerTwoPoints;
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
}