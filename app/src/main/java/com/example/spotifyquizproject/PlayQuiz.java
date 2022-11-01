package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PlayQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        Intent intent = new Intent(PlayQuiz.this, GameScreen.class);
        startActivity(intent);
    }
}