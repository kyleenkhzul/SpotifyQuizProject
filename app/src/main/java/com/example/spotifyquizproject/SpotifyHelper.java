package com.example.spotifyquizproject;

import static com.example.spotifyquizproject.ImportURL.spotURL;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

public class SpotifyHelper extends AppCompatActivity {

    private final String CLIENT_ID;
    private final String REDIRECT_URI;
    private SpotifyAppRemote mSpotifyAppRemote;
    private boolean isPaused = false;


    private int p1Points;
    private int p2Points;
    private int points;



    public SpotifyHelper(){
        this.CLIENT_ID = "0b6a257c37744cfabe83c6949f68019f";
        this.REDIRECT_URI = "http://localhost:8888/callback";
        this.p1Points = 0;
        this.p2Points = 0;
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

    private void connected(String uri) {
        // Play a playlist
        mSpotifyAppRemote.getPlayerApi().setShuffle(true);
        mSpotifyAppRemote.getPlayerApi().play(uri);

        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name);
                    }
                });
    }

    public void connect(){
        connected(spotURL);
    }

    public void pause(){
        if(!isPaused){
            mSpotifyAppRemote.getPlayerApi().pause();
            isPaused = true;
        } else if (isPaused){
            mSpotifyAppRemote.getPlayerApi().resume();
            isPaused = false;
        }
    }

    public void skip(){
        mSpotifyAppRemote.getPlayerApi().skipNext();
    }


    //This method requires an overhaul with points, names, etc. or another helper method to get those
    //It might also need none of these, just double check as this is a potential point of failure
    public void endGame(){
        onStop();
    }

    public SpotifyAppRemote getmSpotifyAppRemote() {
        return mSpotifyAppRemote;
    }

    public void setmSpotifyAppRemote(SpotifyAppRemote mSpotifyAppRemote) {
        this.mSpotifyAppRemote = mSpotifyAppRemote;
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public String getREDIRECT_URI() {
        return REDIRECT_URI;
    }

    public int getP1Points() {
        return p1Points;
    }

    public void setP1Points(int p1Points) {
        this.p1Points = p1Points;
    }

    public int getP2Points() {
        return p2Points;
    }

    public void setP2Points(int p2Points) {
        this.p2Points = p2Points;
    }
}
