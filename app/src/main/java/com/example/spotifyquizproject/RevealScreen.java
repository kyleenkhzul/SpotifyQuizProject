package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RevealScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_screen);


    }

    public void updatePlayerOne(View v){

        TextView textView1 = findViewById(R.id.points1);

        switch(v.getId()){
            case R.id.playerOneIncrement:
                GameScreen.playerOnePoints++;
                break;
            case R.id.playerOneDecrement:
                GameScreen.playerOnePoints--;
                break;
        }

        textView1.setText(Integer.toString(GameScreen.playerOnePoints));
    }


    public void updatePlayerTwo(View v){

        TextView textView2 = findViewById(R.id.points2);

        switch(v.getId()){
            case R.id.playerTwoIncrement:
                GameScreen.playerTwoPoints++;
                break;
            case R.id.playerTwoDecrement:
                GameScreen.playerTwoPoints--;
                break;
        }

        textView2.setText(Integer.toString(GameScreen.playerTwoPoints));
    }


    public void switchBackToGame(View view){
        Intent intent = new Intent(this, GameScreen.class);
        //Changed that from string url to spotURL
        startActivity(intent);
    }





}