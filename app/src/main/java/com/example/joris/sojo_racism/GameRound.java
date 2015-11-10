package com.example.joris.sojo_racism;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * Created by joris on 10/29/2015.
 */
public class GameRound{
    private String[] leftCatagoryList;
    private String[] rightCatagoryList;
    private int[] leftCatagoryImages;
    private int[] rightCatagoryImages;
    int runNumber = 0;
    int maxRunNuber;
    Activity gameActiv;
    Random randomGenerator = new Random(System.nanoTime());

    TextView wordView;
    Button rightButton;
    Button leftButton;
    ImageView imageView;



    public  GameRound(String[] leftCatagoryList0, int[] leftCatagoryImages0, String[] rightCatagoryList0,
                     int[] rightCatagoryImages0, int numbersRuns, Activity activ){

        leftCatagoryList = leftCatagoryList0;
        rightCatagoryList = rightCatagoryList0;
        leftCatagoryImages = leftCatagoryImages0;
        rightCatagoryImages = rightCatagoryImages0;
        maxRunNuber = numbersRuns;
        gameActiv = activ;

        wordView = (TextView) activ.findViewById(R.id.categoryElementTextView);
        imageView = (ImageView) activ.findViewById(R.id.catagoryElementImageview);
        rightButton = (Button) activ.findViewById(R.id.rightGameButton);
        leftButton = (Button) activ.findViewById(R.id.leftGameButton);
    }


    public  GameRound(String[] leftCatagoryList0, String[] rightCatagoryList0, int numbersRuns, Activity activ){
        int[] emptyList = new int[0];
        leftCatagoryList = leftCatagoryList0;
        rightCatagoryList = rightCatagoryList0;
        leftCatagoryImages = emptyList;
        rightCatagoryImages = emptyList;
        maxRunNuber = numbersRuns;
        gameActiv = activ;

        wordView = (TextView) activ.findViewById(R.id.categoryElementTextView);
        imageView = (ImageView) activ.findViewById(R.id.catagoryElementImageview);
        rightButton = (Button) activ.findViewById(R.id.rightGameButton);
        leftButton = (Button) activ.findViewById(R.id.leftGameButton);
        imageView.setVisibility(View.INVISIBLE);
    }

    public  GameRound(int[] leftCatagoryImages0, int[] rightCatagoryImages0, int numbersRuns, Activity activ){
        String[] emptyList = new String[0];
        leftCatagoryList = emptyList;
        rightCatagoryList = emptyList;
        leftCatagoryImages = leftCatagoryImages0;
        rightCatagoryImages = rightCatagoryImages0;
        maxRunNuber = numbersRuns;
        gameActiv = activ;

        wordView = (TextView) activ.findViewById(R.id.categoryElementTextView);
        imageView = (ImageView) activ.findViewById(R.id.catagoryElementImageview);
        rightButton = (Button) activ.findViewById(R.id.rightGameButton);
        leftButton = (Button) activ.findViewById(R.id.leftGameButton);
        imageView.setVisibility(View.VISIBLE);
    }



    public boolean isFinished(){
        return runNumber >= maxRunNuber;
    }

    public int nexWord(){
        String word;
        runNumber ++;
        int imageID;
        double randomNumber;

        if (rightCatagoryImages.length == 0 || leftCatagoryImages.length == 0) {
            //        Randomly chose to pick a entry for the right catagories (>.5) or the left
            if (randomGenerator.nextFloat() > .5) {
                word = rightCatagoryList[randomGenerator.nextInt(rightCatagoryList.length)];
                wordView.setText(word);
                Log.v("MyActivity", "right catagory, setting word: " + word);
                return 0;
            } else {
                word = leftCatagoryList[randomGenerator.nextInt(leftCatagoryList.length)];
                wordView.setText(word);
                Log.v("MyActivity", "left catagory, setting word: " + word);
                return 1;
            }

        }else if (rightCatagoryList.length == 0 || leftCatagoryList.length == 0){
            if (randomGenerator.nextFloat() > .5) {
                imageID = rightCatagoryImages[randomGenerator.nextInt(rightCatagoryImages.length)];
                imageView.setImageResource(imageID);
                Log.v("MyActivity", "right catagory, setting imageID: " + Integer.toString(imageID));
                return 0;
            } else {
                imageID = leftCatagoryImages[randomGenerator.nextInt(leftCatagoryImages.length)];
                imageView.setImageResource(imageID);
                Log.v("MyActivity", "left catagory, setting imageID: " + Integer.toString(imageID));
                return 1;
            }
        }else{
            randomNumber = randomGenerator.nextFloat();

            if (randomNumber <= .25){
                imageView.setVisibility(View.INVISIBLE);
                word = rightCatagoryList[randomGenerator.nextInt(rightCatagoryList.length)];
                wordView.setText(word);
                Log.v("MyActivity", "right catagory, setting word: " + word);
                return 0;
            }else if (randomNumber > .25 && randomNumber <= .5){
                imageView.setVisibility(View.INVISIBLE);
                word = leftCatagoryList[randomGenerator.nextInt(leftCatagoryList.length)];
                wordView.setText(word);
                Log.v("MyActivity", "left catagory, setting word: " + word);
                return 1;

            }else if (randomNumber > .5 && randomNumber <= .75){
                imageView.setVisibility(View.VISIBLE);
                imageID = leftCatagoryImages[randomGenerator.nextInt(leftCatagoryImages.length)];
                imageView.setImageResource(imageID);
                Log.v("MyActivity", "left catagory, setting imageID: " + Integer.toString(imageID));
                return 1;
            }else if (randomNumber > .75){
                imageView.setVisibility(View.VISIBLE);
                imageID = rightCatagoryImages[randomGenerator.nextInt(rightCatagoryImages.length)];
                imageView.setImageResource(imageID);
                Log.v("MyActivity", "right catagory, setting imageID: " + Integer.toString(imageID));
                return 0;
            }
        }
        return 2; //Should never happen!
    }

    public int getRunNumber(){
        return runNumber;
    }
}
