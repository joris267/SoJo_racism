package com.example.joris.sojo_racism;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {
    Button rightButton;
    Button leftButton;
    TextView leftCatagoryTextView;
    TextView rightCatagoryTextView;
    TextView wordView;
    ImageView imageView;
    ImageView errorView;
    GameRound currentRound;
    int roundNumber = 0;
    String[] pleasant = {"caress", "freedom", "health", "love", "peace", "cheer", "friend",
            "heaven", "loyal", "pleasure", "diamond", "gentle", "honest", "lucky", "rainbow",
            "diploma", "gift", "honor", "miracle", "sunrise", "family", "happy", "laughter",
            "paradise", "vacation"};

    String[] unpleasant = {"abuse", "crash", "filth", "murder", "sickness", "accident", "death",
            "grief", "poison", "stink", "assault", "disaster", "hatred", "pollute", "tragedy", "bomb",
            "divorce"};

    int[] white = {R.drawable.avrg_austrian, R.drawable.avrg_french, R.drawable.avrg_ireland,
            R.drawable.avrg_russian, R.drawable.avrg_swiss, R.drawable.avrg_wjite_american};
    int[] black = {R.drawable.avrg_etheopian, R.drawable.avrg_african_american, R.drawable.avrg_central_africa,
            R.drawable.avrg_etheopianf, R.drawable.avrg_south_afrika, R.drawable.avrg_west_african};

    int shortRound = 5;
    int longRound = 10;
    double[] round3List = new double[shortRound];
    double[] round4List = new double[longRound];
    double[] round6List = new double[shortRound];
    double[] round7List = new double[longRound];
    double startTime;


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
        imageView = (ImageView) findViewById(R.id.catagoryElementImageview);
        errorView = (ImageView) findViewById(R.id.errorImageview);

//        Read word lists
//        Instructions
//        Start the game
        nextRound();
    }

    @Override
    public void onBackPressed() {
//        Ignore
    }

    private static String[] combineStringArray(String[] a, String[] b){
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


    private static double[] combineDoubleArray(double[] a, double[] b){
        int length = a.length + b.length;
        double[] result = new double[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }



//    View.OnClickListener correctInput = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if (currentRound.isFinished()){
//                nextRound();
//            }else{
//                nextWord();
//            }
//        }
//    };

    View.OnClickListener falseInput = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            errorView.setVisibility(View.VISIBLE);
        }
    };



    View.OnClickListener correctInput = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double ellapsedTime = (startTime - System.nanoTime()) * 1000; //Convert time to ms.
            switch (roundNumber){
                case 3:
                    //Needs to be minus one since this starts at 0 and run number starts at 1!
                    round3List[currentRound.getRunNumber()-1] = ellapsedTime;
                    break;
                case 4:
                    round4List[currentRound.getRunNumber()-1] = ellapsedTime;
                    break;
                case 6:
                    round6List[currentRound.getRunNumber()-1] = ellapsedTime;
                    break;
                case 7:
                    round7List[currentRound.getRunNumber()-1] = ellapsedTime;
                    break;

            }
            if (currentRound.isFinished()){
                nextRound();
            }else{
                nextWord();
            }
        }
    };



    private void giveExplenation(){
        new AlertDialog.Builder(this)
                .setTitle("Explanation round " + Integer.toString(roundNumber))
                .setMessage("Press the buttons")
                .setPositiveButton("Ok got it", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        nextWord();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void nextWord(){
        errorView.setVisibility(View.INVISIBLE);
        startTime = System.nanoTime();
        if (currentRound.nexWord() == 0){
            rightButton.setOnClickListener(correctInput);
            leftButton.setOnClickListener(falseInput);
        }else{
            rightButton.setOnClickListener(falseInput);
            leftButton.setOnClickListener(correctInput);
        }
    }


//    private void nextMeasuredWord(){
//        if (currentRound.nexWord() == 0){
//            rightButton.setOnClickListener(correctTimedInput);
//            leftButton.setOnClickListener(falseInput);
//        }else{
//            rightButton.setOnClickListener(falseInput);
//            leftButton.setOnClickListener(correctTimedInput);
//        }
//    }


    private double calulateMean(double[] list, double upperCutoff, double lowerCutoff){
        double total = 0;
        int validEntries = 0;
        for (double aList : list) {
            if (aList > lowerCutoff && aList < upperCutoff){
                total += aList;
                validEntries++;
            }
        }
        return total/validEntries;
    }


    private double calulateSTDV(double[] list, double upperCutoff, double lowerCutoff) {
        double total = 0;
        int validEntries = 0;
        double mean = calulateMean(list, upperCutoff, lowerCutoff);
        for (double aList : list) {
            if (aList > lowerCutoff && aList < upperCutoff) {
                total += Math.pow(aList - mean, 2);
            }
        }
        return Math.sqrt(total/mean);
    }


    private void replaceErrorLatencies(double[] list, double stdv, double mean, double upperCutoff, double lowerCutoff){
        for (int i =0; i<list.length; i++){
            if (list[i] < lowerCutoff || list[i] > upperCutoff){
                list[i] = mean + 2 * stdv;
            }
        }
    }


    private double calulateAverage(double[] list){
        double sum = 0;
        for (double d : list) sum += d;
        return sum/list.length;
    }


    private double calculateScore(){
        double upperCutoff = 10000;
        double lowerCutoff = 400;

        double round3Mean = calulateMean(round3List, upperCutoff, lowerCutoff);
        double round4Mean = calulateMean(round4List, upperCutoff, lowerCutoff);
        double round6Mean = calulateMean(round6List, upperCutoff, lowerCutoff);
        double round7Mean = calulateMean(round7List, upperCutoff, lowerCutoff);

        double STDV36 = calulateSTDV(combineDoubleArray(round3List, round6List), upperCutoff, lowerCutoff);
        double STDV47 = calulateSTDV(combineDoubleArray(round4List, round7List), upperCutoff, lowerCutoff);
        double mean36 = calulateMean(combineDoubleArray(round3List, round6List), upperCutoff, lowerCutoff);
        double mean47 = calulateMean(combineDoubleArray(round4List, round7List), upperCutoff, lowerCutoff);


        replaceErrorLatencies(round3List, STDV36, mean36, upperCutoff, lowerCutoff);
        replaceErrorLatencies(round4List, STDV47, mean47, upperCutoff, lowerCutoff);
        replaceErrorLatencies(round6List, STDV36, mean36, upperCutoff, lowerCutoff);
        replaceErrorLatencies(round7List, STDV47, mean47, upperCutoff, lowerCutoff);

        double averageBlock3 = calulateAverage(round3List);
        double averageBlock4 = calulateAverage(round4List);
        double averageBlock6 = calulateAverage(round6List);
        double averageBlock7 = calulateAverage(round7List);

        double weightedDifference36 = (averageBlock6 - averageBlock3) / STDV36;
        double weightedDifference47 = (averageBlock7 - averageBlock4) / STDV47;

        return (weightedDifference36 + weightedDifference47)/2.0;
    }


    private void nextRound(){

        switch (roundNumber){
            case 0:
                giveExplenation();
                leftCatagoryTextView.setText("Pleasant");
                rightCatagoryTextView.setText("Unpleasant");
                currentRound = new GameRound(pleasant, unpleasant, shortRound, this);
                break;
            case 1:
                giveExplenation();
                leftCatagoryTextView.setText("White");
                rightCatagoryTextView.setText("Black");
                currentRound = new GameRound(white, black, shortRound, this);
                break;
            case 2:
                giveExplenation();
                leftCatagoryTextView.setText("White \n or \n unpleasant");
                rightCatagoryTextView.setText("Black \n or \n pleasant");
                currentRound = new GameRound(unpleasant, white, pleasant, black, shortRound, this);
                break;
            case 3:
                giveExplenation();
                leftCatagoryTextView.setText("White \n or \n unpleasant");
                rightCatagoryTextView.setText("Black \n or \n pleasant");
                currentRound = new GameRound(unpleasant, white, pleasant, black, longRound, this);
                break;
            case 4:
                giveExplenation();
                leftCatagoryTextView.setText("Black");
                rightCatagoryTextView.setText("White");
                currentRound = new GameRound(black, white, shortRound, this);
                break;
            case 5:
                giveExplenation();
                leftCatagoryTextView.setText("Black \n or \n unpleasant");
                rightCatagoryTextView.setText("White \n or \n pleasant");
                currentRound = new GameRound(unpleasant, black, pleasant, white, shortRound, this);
                break;
            case 6:
                giveExplenation();
                leftCatagoryTextView.setText("Black \n or \n unpleasant");
                rightCatagoryTextView.setText("White \n or \n pleasant");
                currentRound = new GameRound(unpleasant, black, pleasant, white, longRound, this);
                break;
            case 7:
                double gameScore = calculateScore();
                imageView.setVisibility(View.INVISIBLE);
                wordView.setText(String.valueOf(gameScore));
//                Intent finished = new Intent(this, HighscoreActivity.class);
//                startActivity(finished);

        }
        roundNumber++;

//        Calculate score and go to highscores
    }


}
