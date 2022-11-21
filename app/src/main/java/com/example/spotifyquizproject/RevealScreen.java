package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RevealScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_screen);
        Log.d("points", "points" + MainActivity.spotifyHelper.getP1Points());


    }

    public void updatePlayerOne(View v){

        TextView textView1 = findViewById(R.id.points1);

        switch(v.getId()){
            case R.id.playerOneIncrement:
                MainActivity.spotifyHelper.setP1Points(MainActivity.spotifyHelper.getP1Points() + 1);
                break;
            case R.id.playerOneDecrement:
                MainActivity.spotifyHelper.setP1Points(MainActivity.spotifyHelper.getP1Points() - 1);
                break;
        }

        textView1.setText(Integer.toString(MainActivity.spotifyHelper.getP1Points()));
    }


    public void updatePlayerTwo(View v){

        TextView textView2 = findViewById(R.id.points2);

        switch(v.getId()){
            case R.id.playerTwoIncrement:
                MainActivity.spotifyHelper.setP2Points(MainActivity.spotifyHelper.getP2Points() + 1);
                break;
            case R.id.playerTwoDecrement:
                MainActivity.spotifyHelper.setP2Points(MainActivity.spotifyHelper.getP2Points() - 1);
                break;
        }

        textView2.setText(Integer.toString(MainActivity.spotifyHelper.getP2Points()));
    }


    public void switchBackToGame(View view){
        Intent intent = new Intent(this, GameScreen.class);
        //Changed that from string url to spotURL
        startActivity(intent);
    }





}