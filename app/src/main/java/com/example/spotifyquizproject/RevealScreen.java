package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RevealScreen extends AppCompatActivity {

    private static int playerOnePoints;
    private static int playerTwoPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_screen);


    }

    public void updatePlayerOne(View v){
        TextView textView = findViewById(R.id.points1);
        playerOnePoints = Integer.parseInt(textView.getText().toString());

        switch(v.getId()){
            case R.id.playerOneIncrement:
                playerOnePoints++;
                break;
            case R.id.playerOneDecrement:
                playerOnePoints--;
                break;
        }

        textView.setText(Integer.toString(playerOnePoints));
    }


    public void updatePlayerTwo(View v){
        TextView textView = findViewById(R.id.points2);
        playerTwoPoints = Integer.parseInt(textView.getText().toString());

        switch(v.getId()){
            case R.id.playerTwoIncrement:
                playerTwoPoints++;
                break;
            case R.id.playerTwoDecrement:
                playerTwoPoints--;
                break;
        }

        textView.setText(Integer.toString(playerTwoPoints));
    }


    public void switchBackToGame(View view){
        startActivity(new Intent(getApplicationContext(), GameScreen.class));
    }

    public void switchToEnd(View view){
        startActivity(new Intent(getApplicationContext(), EndScreen.class));
    }




}