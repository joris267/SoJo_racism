package com.example.joris.sojo_racism;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;


/**
 * Created by joris on 10/29/2015.
 */
public class GameRound{
    private String[] leftCatagoryList;
    private String[] rightCatagoryList;
    int runNumber = 0;
    int maxRunNuber;
    Activity gameActiv;
    Random randomGenerator = new Random();
    TextView wordView;
    Button rightButton;
    Button leftButton;


    public  GameRound(String[] leftCatagoryList0, String[] rightCatagoryList0,
                     int numbersRuns, Activity activ){

        leftCatagoryList = leftCatagoryList0;
        rightCatagoryList = rightCatagoryList0;
        maxRunNuber = numbersRuns;
        gameActiv = activ;
        wordView = (TextView) activ.findViewById(R.id.categoryElementTextView);
        rightButton = (Button) activ.findViewById(R.id.rightGameButton);
        leftButton = (Button) activ.findViewById(R.id.leftGameButton);
    }


    public boolean isFinished(){
        return runNumber >= maxRunNuber;
    }

    public int nexWord(){

        String word;
        runNumber ++;

//        Randomly chose to pick a entry for the right catagories (>.5) or the left
        if (randomGenerator.nextFloat() > .5){
            word = rightCatagoryList[randomGenerator.nextInt(rightCatagoryList.length)];
            wordView.setText(word);
            return 0;
        }else{
            word = leftCatagoryList[randomGenerator.nextInt(leftCatagoryList.length)];
            wordView.setText(word);
            return 1;
        }
    }

    public int getRunNumber(){
        return runNumber;
    }
}
