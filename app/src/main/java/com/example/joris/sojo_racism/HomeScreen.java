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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*------------------------------------------------------------------------------------------------*/
/* Navigation Methods                                                                             */
/*------------------------------------------------------------------------------------------------*/

    /* Method navigates to the activity that starts the name selection before the test. */
    public void playGame(View view) {

        Intent playGame = new Intent(this, GameActivity.class);
        startActivity(playGame);
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
    }

    /* Method navigates to the activity that shows all saved scores. */
    public void showScores(View view) {
    }

    /* Method shows dialog showing the score explanation. */
    public void showScoreExplanation(View view) {
    }

    /* Method shows dialog showing the different personalities used to categorize the users. */
    public void showPersonalities(View view) {
    }
}