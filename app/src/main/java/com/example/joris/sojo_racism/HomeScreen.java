/* HomeScreen Class
 * Class type: Activity
 *
 * Class implements all methods that navigate to the different options in the homeScreen menu.
 *
 * Authors: Michiel Boswijk & Joris Schefold
 * Contact: michiel.boswijk@gmail.com
 * Last updated: 23-11-2015
 */

/* Reference package. */
package com.example.joris.sojo_racism;

/* Necessary imports. */
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

//TODO: Decide on personalities
//TODO: Decide on modes
//TODO: Decide on screen orientations (restoreInstanceState, etc)

public class HomeScreen extends AppCompatActivity {

/*------------------------------------------------------------------------------------------------*/
/* Override Methods                                                                               */
/*------------------------------------------------------------------------------------------------*/

    /* Method called on creation of the class. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Call constructor superclass and link layout file. */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

/*------------------------------------------------------------------------------------------------*/
/* Navigation Methods                                                                             */
/*------------------------------------------------------------------------------------------------*/

    /* Method navigates to the activity that starts the name selection before the test. */
    public void playGame(View view) {

        Intent startInfoInput = new Intent(this, InfoInput.class);
        startActivity(startInfoInput);
    }

    /* Method shows dialog showing the 'about us' information. */
    public void showAboutUs(View view) {

        DialogFragment aboutUs = new AboutUs();
        aboutUs.show(getFragmentManager(), "dialog");
    }

    /* Method shows dialog showing the 'how to play' information. */
    public void showHowToPlay(View view) {

        DialogFragment howToPlay = new HowToPlay();
        howToPlay.show(getFragmentManager(), "dialog");
    }

    /* Method navigates to the activity that allows the user to change the game mode. */
    public void changeMode(View view) {

        Toast.makeText(getApplicationContext(), "Do we want multiple modes?", Toast.LENGTH_LONG).show();
    }

    /* Method navigates to the activity that shows all saved scores. */
    public void showScores(View view) {

        Intent showScores = new Intent(this, HighscoreActivity.class);
        startActivity(showScores);
    }

    /* Method shows dialog showing the score explanation. */
    public void showScoreExplanation(View view) {

        DialogFragment scoreExplanation = new ScoreExplanation();
        scoreExplanation.show(getFragmentManager(), "dialog");
    }

    /* Method shows dialog showing the different personalities used to categorize the users. */
    public void showPersonalities(View view) {

        Toast.makeText(getApplicationContext(), "To be determined.", Toast.LENGTH_LONG).show();
    }
}