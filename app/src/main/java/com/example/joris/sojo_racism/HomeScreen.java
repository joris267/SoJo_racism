package com.example.joris.sojo_racism;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // test to see if the reader works
        /*WordReader testWordReader = new WordReader(getApplicationContext());
        ArrayList<String> testList = testWordReader.readWords("black");
        TextView testView = (TextView) findViewById(R.id.test);
        testView.setText(testList.get(1));*/


        // test to see if the data communication workds (readin/writing new player data)
        /*ScoreData testScoreData = new ScoreData(getApplicationContext());
        float testScore = (float) 0.0;
        float testScore2 = (float) 2.0;
        Player joris = new Player("Joris Schefold", testScore, "");
        Player frits = new Player("Fritsje Papi", testScore2, "");
        Player jan = new Player("Jan Janssen", testScore2, "");
        testScoreData.add(joris);
        testScoreData.add(frits);
        testScoreData.add(jan);

        ScoreData testScoreData2 = new ScoreData(getApplicationContext());
        ArrayList<Player> testList = testScoreData2.getList();
        TextView testView = (TextView) findViewById(R.id.test);
        testView.setText(testList.get(30).getDate());
        System.out.println(testList.size());
        for (Player player : testList) {
            System.out.println(player.getName() + " hoi");
        }*/
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

    public void playGame(View view) {
        Intent playGame = new Intent(this, GameActivity.class);
        startActivity(playGame);

    }
}
