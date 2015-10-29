package com.example.joris.sojo_racism;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {
    Button rightBbutton;
    Button leftButton;
    TextView leftCatagoryText;
    TextView rightCatagoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rightBbutton = (Button) findViewById(R.id.rightGameButton);
        leftButton = (Button) findViewById(R.id.leftGameButton);
        leftCatagoryText = (TextView) findViewById(R.id.leftGameCatagory);
        rightCatagoryText = (TextView) findViewById(R.id.rightGameCatagory);

//        Read word lists
//        Start the game
    }



    private void playGame(){
//        Instructions
//        playRound
//        repeat

//        Calculate score and go to highscores
    }


    public void playRound(String[] leftCatagoryList, String leftCatagoryName,
                           String[] rightCatagoryList, String rightCatagoryName, int numbers){

        leftCatagoryText.setText(leftCatagoryName);
        rightCatagoryText.setText(rightCatagoryName);

//        GameRound firstRound = new GameRound( ,this)


    }

}
