package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class RevealScreen extends AppCompatActivity {
    public static int Player1Points = 0;
    public static int Player2Points = 0;
    public static int count = 0;
    private ImageView albumArtwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_screen);

        Intent intent = getIntent();
        String uriToShow = intent.getStringExtra("uriToShow");
        albumArtwork = findViewById(R.id.songReveal);
        Picasso.get().load(uriToShow).into(albumArtwork);

        TextView p1 = findViewById(R.id.points1);
        p1.setText(Integer.toString(Player1Points));

        TextView p2 = findViewById(R.id.points2);
        p2.setText(Integer.toString(Player2Points));

        Log.d("points", "points" + MainActivity.spotifyHelper.getP1Points());
        Log.d("name", "name: " + GameScreen.name);
        Log.d("uri", "uri: " + GameScreen.imageUri);
        Log.d("uri", uriToShow);
//        String uri = GameScreen.imageUri.substring(GameScreen.imageUri.indexOf("{") + 1, GameScreen.imageUri.indexOf("'"));
//        Log.d("text", "text: " + uri);

        TextView answer = findViewById(R.id.answer);
        answer.setText(GameScreen.name + " by " + GameScreen.artist);
    }

    public void updatePlayerOne(View v){
        TextView textView1 = findViewById(R.id.points1);
        switch(v.getId()){
            case R.id.playerOneIncrement:
//                MainActivity.spotifyHelper.setP1Points(MainActivity.spotifyHelper.getP1Points() + 1);
                Player1Points++;
                break;
            case R.id.playerOneDecrement:
//                MainActivity.spotifyHelper.setP1Points(MainActivity.spotifyHelper.getP1Points() - 1);
                Player1Points--;
                break;
        }

        textView1.setText(Integer.toString(Player1Points));
    }

    public void updatePlayerTwo(View v){
        TextView textView2 = findViewById(R.id.points2);
        switch(v.getId()){
            case R.id.playerTwoIncrement:
//              MainActivity.spotifyHelper.setP2Points(MainActivity.spotifyHelper.getP2Points() + 1);
                Player2Points++;
                break;
            case R.id.playerTwoDecrement:
//              MainActivity.spotifyHelper.setP2Points(MainActivity.spotifyHelper.getP2Points() - 1);
                Player2Points--;
                break;
        }
        textView2.setText(Integer.toString(Player2Points));
    }

    public void switchBackToGame(View view){
        count++;
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("P1Points", Player1Points);
        intent.putExtra("P2Points", Player2Points);
        startActivity(intent);
    }
}