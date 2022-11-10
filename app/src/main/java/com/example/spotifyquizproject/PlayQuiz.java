package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayQuiz extends AppCompatActivity {
    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        Bundle bundle = getIntent().getExtras();
        text = bundle.getString("inputText");
    }


    public void switchToGame(View view){
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("uriText", text);
        startActivity(intent);
    }


}