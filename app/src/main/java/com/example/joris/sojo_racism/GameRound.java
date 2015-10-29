package com.example.joris.sojo_racism;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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


    public GameRound(String[] leftCatagoryList0, String[] rightCatagoryList0,
                     int numbers, Activity activ){

        leftCatagoryList = leftCatagoryList0;
        rightCatagoryList = rightCatagoryList0;
        maxRunNuber = numbers;
        gameActiv = activ;
        wordView = (TextView) activ.findViewById(R.id.categoryElementTextView);
        rightButton = (Button) activ.findViewById(R.id.rightGameButton);
        leftButton = (Button) activ.findViewById(R.id.leftGameButton);
    }


    View.OnClickListener correctInput = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nexWord();
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


    public boolean nexWord(){
        if (runNumber >= maxRunNuber){
//            Do something
            return false;
        }

        String word;
//        Randomly chose to pick a entry for the right catagories (>.5) or the left
        if (randomGenerator.nextFloat() > .5){
            word = rightCatagoryList[randomGenerator.nextInt(rightCatagoryList.length)];
            wordView.setText(word);
            rightButton.setOnClickListener(correctInput);
            leftButton.setOnClickListener(falseInput);

        }else{
            word = leftCatagoryList[randomGenerator.nextInt(leftCatagoryList.length)];
            wordView.setText(word);
            rightButton.setOnClickListener(falseInput);
            leftButton.setOnClickListener(correctInput);
        }
        runNumber ++;
        return true;
    }
}
