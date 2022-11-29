package com.example.spotifyquizproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;
import com.spotify.protocol.types.ImageUri;
import com.spotify.protocol.types.Track;
import com.squareup.picasso.Picasso;

public class RevealScreen extends AppCompatActivity {
    Picasso picasso;
    ImageUri uri;
    private static int P1Points = 0;
    private static int P2Points = 0;
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_screen);
        TextView p1 = findViewById(R.id.points1);
        p1.setText(Integer.toString(P1Points));

        TextView p2 = findViewById(R.id.points2);
        p2.setText(Integer.toString(P2Points));

        Log.d("points", "points" + MainActivity.spotifyHelper.getP1Points());

        uri = MainActivity.spotifyHelper.getSongURI();
        ImageView imageView = findViewById(R.id.songReveal);
        picasso.get().load(uri.toString()).into(imageView);
    }

    public void updatePlayerOne(View v){
        TextView textView1 = findViewById(R.id.points1);
        switch(v.getId()){
            case R.id.playerOneIncrement:
//                MainActivity.spotifyHelper.setP1Points(MainActivity.spotifyHelper.getP1Points() + 1);
                P1Points++;
                break;
            case R.id.playerOneDecrement:
//                MainActivity.spotifyHelper.setP1Points(MainActivity.spotifyHelper.getP1Points() - 1);
                P1Points--;
                break;
        }

        textView1.setText(Integer.toString(P1Points));
    }

    public void updatePlayerTwo(View v){
        TextView textView2 = findViewById(R.id.points2);
        switch(v.getId()){
            case R.id.playerTwoIncrement:
//              MainActivity.spotifyHelper.setP2Points(MainActivity.spotifyHelper.getP2Points() + 1);
                P2Points++;
                break;
            case R.id.playerTwoDecrement:
//              MainActivity.spotifyHelper.setP2Points(MainActivity.spotifyHelper.getP2Points() - 1);
                P2Points--;
                break;
        }
        textView2.setText(Integer.toString(P2Points));
    }


    public void switchBackToGame(View view){
        count++;
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("P1Points", P1Points);
        intent.putExtra("P2Points", P2Points);
        startActivity(intent);
    }





}