/* Scores Class
 * Class type: Activity
 *
 * Class implements the activity that displays the scores of the players saved in memory.
 * Displayed information: Player's name, score and date on which the test was taken.
 *
 * Authors: Michiel Boswijk & Joris Schefold
 * Contact: michiel.boswijk@gmail.com
 * Last updated: 23-11-2015
 */

/* Reference package. */
package com.example.joris.sojo_racism;

/* Necessary imports. */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity {

    /* Method called on creation of the activity. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        /* Make a score object that reads all player data. */
        ScoreData scores = new ScoreData(getApplicationContext());

        // Add players to test the highscores view.
        /*scores.add(new Player("Namey McNamesson", (float) 1.1, ""));
        scores.add(new Player("Joris Schefold", (float) 0.3, ""));
        scores.add(new Player("Michiel Boswijk", (float) 10.1, ""));
        scores.add(new Player("Jon Snow", (float) -12.1, ""));
        scores.add(new Player("Arya Stark", (float) 1.0, ""));
        scores.readPlayerData();*/

        /* Get the list with all players and their data. */
        ArrayList<Player> players = scores.getList();

        /* Set link the custom adapter to the playerList and the listView widget. */
        ArrayAdapter myAdapter = new ScoreAdapter(HighscoreActivity.this, players);
        ListView highscoresView = (ListView) findViewById(R.id.scores_view);
        highscoresView.setAdapter(myAdapter);
    }
}