/* InfoInput Class
 * Class type: Activity
 *
 * Class implements a form that the user has to complete before starting the test.
 * It contains methods for checking valid inputs for each of the fields in the form.
 *
 * Authors: Michiel Boswijk & Joris Schefold
 * Contact: michiel.boswijk@gmail.com
 * Last updated: 28-11-2015
 */

/* Reference package. */
package com.example.joris.sojo_racism;

/* Necessary imports. */
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

//TODO: Change editText highlight color (after Mees redesigned)
//TODO: Decide whether we allow for duplicate names
//TODO: Decide on ethnicities (list or own input etc). Hard to stay PC
//TODO: Edit minimum age?
//TODO: Save the user data somewhere (probably once the test is completed save all data)

public class InfoInput extends AppCompatActivity {

    /* Declare/Initialize class variables. */
    private TextView asterisk1, asterisk2, asterisk3, asterisk4, required;
    private EditText nameField, ageField;
    private Spinner genderSpinner, ethnicitySpinner;
    private String[] genders = {"", "Male", "Female", "Trans"}; //TODO: Not most elegant solution empty string
    private String[] ethnicities = {"", "Arab", "American Indian", "Negro", "Asian", "Caucasian", "None of the above"};

    /* Method called on creation of the class. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Call constructor superclass and link layout file. */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_input);

        /* Initialize all views, link the adapters for the spinners and apply any colors. */
        initViews();
        linkAdapters();
        applyColors();
    }

    /* Method for allowing the user to hide keyboard when pressed outside of keyboard. */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return true;
    }

    /* Method for initializing all views in the activity. */
    public void initViews() {

        /* Initialize views used in the activity. */
        asterisk1 = (TextView) findViewById(R.id.asterisk1);
        asterisk2 = (TextView) findViewById(R.id.asterisk2);
        asterisk3 = (TextView) findViewById(R.id.asterisk3);
        asterisk4 = (TextView) findViewById(R.id.asterisk4);
        required = (TextView) findViewById(R.id.required_field);
        nameField = (EditText) findViewById(R.id.name_input);
        ageField = (EditText) findViewById(R.id.age_input);
        genderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        ethnicitySpinner = (Spinner) findViewById(R.id.ethnicity_spinner);
    }

    /* Method for linking the arrays to adapters, and adapters to the spinners. */
    public void linkAdapters() {

        /* Declare variables. */
        ArrayAdapter<String> genderAdapter, ethnicityAdapter;

        /* Link genders/ethnicities to adapter and link adapter to spinner. */
        genderAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_layout, genders);
        ethnicityAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_layout, ethnicities);
        genderAdapter.setDropDownViewResource(R.layout.spinner_layout);
        ethnicityAdapter.setDropDownViewResource(R.layout.spinner_layout);
        genderSpinner.setAdapter(genderAdapter);
        ethnicitySpinner.setAdapter(ethnicityAdapter);
    }

    /* Method for applying all colors in the activity. */
    public void applyColors() {

        /* Set apply color to the asterisks indicating required fields. */
        asterisk1.setTextColor(getResources().getColor(R.color.red));
        asterisk2.setTextColor(getResources().getColor(R.color.red));
        asterisk3.setTextColor(getResources().getColor(R.color.red));
        asterisk4.setTextColor(getResources().getColor(R.color.red));
        required.setTextColor(getResources().getColor(R.color.red));
    }

    /* Method for starting the test activity. */
    public void takeTest(View view) {

        /* If all input values are valid, proceed to the test activity. */
        if(checkName() && checkAge() && checkGender() && checkEthnicity()) {
            Intent playGame = new Intent(this, GameActivity.class);
            startActivity(playGame);
        }
    }

/*------------------------------------------------------------------------------------------------*/
/* Field Validation Methods                                                                       */
/*------------------------------------------------------------------------------------------------*/

    /* Method for checking whether the entered name is valid. */
    public boolean checkName() {

        /* Read name from the input. */
        String name = String.valueOf(nameField.getText());

        /* Check criteria concerning length and char types and display a toast when something is wrong. */
        if (name.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
        } else if (name.length() > 20) {
            Toast.makeText(getApplicationContext(), "Please use a shorter name.", Toast.LENGTH_SHORT).show();
        } else if (!name.matches("[a-zA-Z\\s]+")) {
            Toast.makeText(getApplicationContext(), "Please only use letters and spaces.", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    /* Method for checking whether the entered age is valid. */
    public boolean checkAge() {

        /* Declare local variable and read age from the input. */
        int age;
        String ageString = String.valueOf(ageField.getText());

        /* If age is not empty and between reasonable bounds, the age is valid. */
        if(!ageString.equals("")){
            age = Integer.parseInt(ageString);
            if(age >= 5 && age <= 122) {
                return true;
            }
        }

        /* Show message indicating that the age is not valid yet. */
        Toast.makeText(getApplicationContext(), "Please enter a valid age.", Toast.LENGTH_SHORT).show();
        return false;
    }

    /* Method for checking whether the user has selected a gender. */
    public boolean checkGender() {

        /* Get selected spinner item. */
        String gender = genderSpinner.getSelectedItem().toString();

        /* Check if a gender is selected. */
        if(!gender.equals("")) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Please select a gender.", Toast.LENGTH_SHORT).show();
        }
         return false;
    }

    /* Method for checking whether the user has selected an ethnicity. */
    public boolean checkEthnicity() {

        /* Get selected spinner item. */
        String ethnicity = ethnicitySpinner.getSelectedItem().toString();

        /* Check if an ethnicity is selected. */
        if(!ethnicity.equals("")) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Please select an ethnicity.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}