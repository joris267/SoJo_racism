/* ScoreAdapter class
 * Class type: Helper class
 *
 * Class implements an adapter used to link the list of all players to the list view where
 * the scores are displayed.
 *
 * Authors: Michiel Boswijk & Joris Schefold
 * Contact: michiel.boswijk@gmail.com
 * Last updated: 23-11-2015
 */

//TODO: Check of context nodig is (ivm met de warning)

/* Reference package. */
package com.example.joris.sojo_racism;

/* Necessary imports. */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<Player> {

    /* Declare class variables. */
    private ArrayList<Player> allPlayers;
    //private Context context;

    /* Constructor initializes class variables and sorts player list. */
    public ScoreAdapter(Context context, ArrayList<Player> players) {

        /* Link layout to adapter. */
        super(context, R.layout.scores_layout, players);

        /* Initialize class variables. */
        //this.context = context;
        allPlayers = players;
    }

    /* Method for setting all info and layout to be displayed in a row of the scores. */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /* Create and link layout inflater for linking layout to the view objects. */
        LayoutInflater myInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View theView = myInflater.inflate(R.layout.scores_layout, parent, false);

        /* Get name, date and score of every player.*/
        Player player = allPlayers.get(position);
        String name = player.getName();
        String date = player.getDate();
        String score = Float.toString(player.getScore());

        /* Set name, score and date of each of the rows in the score view. */
        TextView nameView = (TextView) theView.findViewById(R.id.scores_name);
        TextView scoreView = (TextView) theView.findViewById(R.id.scores_score);
        TextView dateView = (TextView) theView.findViewById(R.id.scores_date);
        nameView.setText(name);
        scoreView.setText("Score: " + score);
        dateView.setText("Date: " + date);

        return theView;
    }
}