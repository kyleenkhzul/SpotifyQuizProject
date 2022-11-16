package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
    }

    public void switchToStart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}