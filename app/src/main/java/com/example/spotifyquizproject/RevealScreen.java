package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RevealScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_screen);

        Intent intent = new Intent(RevealScreen.this, GameScreen.class);
        startActivity(intent);

        Intent intent2 = new Intent(RevealScreen.this, EndScreen.class);
        startActivity(intent2);
    }
}