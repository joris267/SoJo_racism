
package com.example.joris.sojo_racism;

import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Add does not work yet

public class ScoreData {

    private final String PLAYER_DATA_FILE =  "player_data_file";
    private ArrayList<Player> players;
    private Context context;

    public ScoreData(Context context) {

        this.context = context;
        players = new ArrayList<>();
        readPlayerData();
    }

    /* Method for reading names from the txt file. */
    public void readPlayerData() {
        /* Declare local variables. */
        Scanner myScanner;
        String playerInfo, name, date;
        float score;

        /* Try to open the text file, obtain the data and write them as players to player list. */
        try {
            myScanner = new Scanner(context.openFileInput(PLAYER_DATA_FILE));
            while (myScanner.hasNextLine()) {
                playerInfo = myScanner.nextLine();
                /* Since names, scores and dates are divided by a comma in the txt file,
                 * the line is split on spaces and the data is added in a new player to the list.
                 */
                String[] split = playerInfo.split(",");
                name = split[0];
                score = Float.parseFloat(split[1]);
                date = split[2];
                players.add(new Player(name, score, date));
            }
        /* When file is not found, display message stating no players are in memory. */
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_LONG).show();
        }
    }

    /* Method for adding a player to the text file. */
    public void add(Player player) {
        PrintStream output;

        /* Try to open the file and write data to it. */
        try {
            /* Get name, score and date from player. */
            output = new PrintStream(context.openFileOutput(PLAYER_DATA_FILE, Context.MODE_APPEND));
            String playerName = player.getName();
            String playerScore = Float.toString(player.getScore());
            if (player.getDate().equals("")) { // Makes sure only new players have their date updated.
                player.setDate();
            }
            String playerDate = player.getDate();

            /* Write data (space-separated) as a line to the file. */
            String line = (playerName + "," + playerScore + "," + playerDate);
            output.println(line);
            output.close();

        /* Display message when file can not be opened. */
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    /* Return the list of players as an ArrayList. */
    public ArrayList<Player> getList () {
        return players;
    }

    /* Return names as an ArrayList. */
    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Player player : players) {
            names.add(player.getName());
        }
        return names;
    }
}