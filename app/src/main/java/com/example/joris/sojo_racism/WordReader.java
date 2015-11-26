/* Reader class
 * Class type: Helper class
 *
 * Class implements an object for reading the different txt files containing single words per line.
 * Contains methods for returning different lists containing the data from the txt files.
 *
 * Author: Michiel Boswijk, michiel.boswijk@gmail.com
 * Last updated: 27-10-2015
 */

/* Reference package. */
package com.example.joris.sojo_racism;

/* Necessary imports. */
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordReader {

    /* Declare class variable. */
    private Context context;

    /* Constructor initializes class variable. */
    public WordReader(Context context) {
        this.context = context;
    }

    /* Method for reading and returning words from the txt files. */
    public ArrayList<String> readWords(String keyword) {

        /* Initialize/Declare local variables. */
        ArrayList<String> words = new ArrayList<>();
        String fileName;
        Scanner myScanner;
        String line;

        /* Determine which txt file to read from based on the given keyword. */
        switch(keyword) {
            case "pleasant":
                fileName = "words_pleasant";
                break;
            case "unpleasant":
                fileName = "words_unpleasant";
                break;
            case "black":
                fileName = "words_race_black";
                break;
            case "white":
                fileName = "words_race_white";
                break;
            default:
                fileName = "";
                break;
        }

        /* Try to read all words from the chosen .txt file and add to list of words. */
        try {
            myScanner = new Scanner(context.getAssets().open(fileName));
            while (myScanner.hasNextLine()) {
                line = myScanner.nextLine();
                words.add(line);
            }
        } catch (IOException e) {
            Toast.makeText(context, "Error opening the file", Toast.LENGTH_LONG).show();
        }

        return words;
    }
}