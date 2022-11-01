package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ImportURL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importurl);

        Intent intent = new Intent(ImportURL.this, PlayQuiz.class);
        startActivity(intent);
    }
}