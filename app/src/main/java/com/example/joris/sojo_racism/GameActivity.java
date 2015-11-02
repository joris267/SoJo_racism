package com.example.joris.sojo_racism;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {
    Button rightButton;
    Button leftButton;
    TextView leftCatagoryTextView;
    TextView rightCatagoryTextView;
    TextView wordView;
    GameRound currentRound;
    int roundNumber = 0;
    String[] pleasant = {"good0", "good1", "good2"};
    String[] unpleasant = {"bad0", "bad1", "bad2"};
    String[] white = {"white0", "white1", "white2"};
    String[] black = {"black0", "black1", "black2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rightButton = (Button) findViewById(R.id.rightGameButton);
        leftButton = (Button) findViewById(R.id.leftGameButton);
        leftCatagoryTextView = (TextView) findViewById(R.id.leftGameCatagory);
        rightCatagoryTextView = (TextView) findViewById(R.id.rightGameCatagory);
        wordView = (TextView) findViewById(R.id.categoryElementTextView);

//        Read word lists
//        Instructions
//        Start the game
        nextRound();
    }

    @Override
    public void onBackPressed() {
//        Ignore
    }

    private static String[] combine(String[] a, String[] b){
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


    View.OnClickListener correctInput = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (currentRound.isFinished()){
                nextRound();
            }else{
                nextWord();
            }
        }
    };

    View.OnClickListener falseInput = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String word = (String) wordView.getText();
            word = word + "!!!";
            wordView.setText(word);
        }
    };


    private void giveExplenation(){
        new AlertDialog.Builder(this)
                .setTitle("Explanation round " + Integer.toString(roundNumber))
                .setMessage("Press the buttons")
                .setPositiveButton("Ok got it", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        Cool you get it
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    private void nextWord(){
        if (currentRound.nexWord() == 0){
            rightButton.setOnClickListener(correctInput);
            leftButton.setOnClickListener(falseInput);
        }else{
            rightButton.setOnClickListener(falseInput);
            leftButton.setOnClickListener(correctInput);
        }
    }

    private void nextRound(){
        switch (roundNumber){
            case 0:
                giveExplenation();
                leftCatagoryTextView.setText("Pleasant");
                rightCatagoryTextView.setText("Unpleasant");
                currentRound = new GameRound(pleasant, unpleasant, 10, this);
                nextWord();
                break;
            case 1:
                giveExplenation();
                leftCatagoryTextView.setText("White");
                rightCatagoryTextView.setText("Black");
                currentRound = new GameRound(white, black, 10, this);
                nextWord();
                break;
            case 2:
                leftCatagoryTextView.setText("White \n or \n unpleasant");
                rightCatagoryTextView.setText("Black \n or \n pleasant");
                currentRound = new GameRound(combine(unpleasant, white), combine(pleasant, black), 10, this);
                nextWord();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                Intent finished = new Intent(this, HighscoreActivity.class);
                startActivity(finished);

        }
        roundNumber++;
//        Calculate score and go to highscores
    }


}
