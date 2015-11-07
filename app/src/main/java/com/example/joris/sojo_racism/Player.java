/*
 * Player class
 *
 * Class implements a player object.
 * A player has a name, score and 'last played' date.
 *
 * Author: Michiel Boswijk, michiel.boswijk@gmail.com
 * Last updated: 16-10-2015
 */

package com.example.joris.sojo_racism;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// Might need to make it comparable  (implements Comparable<Player>)

public class Player{

    /* Declare class variables. */
    private String name, date;
    private float score;

    /* Constructor initializes class variables passed as arguments. */
    public Player(String playerName, float playerScore, String lastPlayed){
        name = playerName;
        score = playerScore;
        date = lastPlayed;
    }

    /* Method for returning the date of the player. */
    public String getDate() {
        return date;
    }

    /* Method for setting the current date to the players date property. */
    public void setDate() {
        /* Obtain current date and format into dd-mm-yyyy. */
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        date = df.format(c.getTime());
        System.out.println(date);
    }

    /* Method for getting the name if the player. */
    public String getName() {
        return name;
    }

    /* Method for setting the name if the player. */
    public void setName(String name) {
        this.name = name;
    }

    /* Method for getting the score of the player. */
    public float getScore() {
        return score;
    }

    /* Method assists the sorting of player objects. Causes the players to be sorted by score. *//*
    public float compareTo(@NonNull Player comparePlayer){
        float compareScore = comparePlayer.getScore();
        return compareScore - score;
    }*/
}