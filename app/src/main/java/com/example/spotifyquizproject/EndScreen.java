package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {
    TextView play;
    TextView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        //double check this, source of error
        GameScreen.compareValues(MainActivity.spotifyHelper.getP1Points(), MainActivity.spotifyHelper.getP1Points());
        play = findViewById(R.id.playerNumber);
        point = findViewById(R.id.pointValue);
        values();
    }

    public void switchToStart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void values(){
        if(RevealScreen.Player1Points > RevealScreen.Player2Points){
            play.setText("Player 1");
            point.setText(RevealScreen.Player1Points + " Points!");
        }else if(RevealScreen.Player1Points < RevealScreen.Player2Points){
            play.setText("Player 2");
            point.setText(RevealScreen.Player2Points + " Points!");
        }else{
            play.setText("Everybody");
            point.setText(RevealScreen.Player1Points + " Points!");
        }
    }
}