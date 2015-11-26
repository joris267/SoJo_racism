/* ScoreExplanation Class
 * Class type: Dialog
 *
 * Class implements a dialog showing the information about the scores en their categories.
 *
 * Authors: Michiel Boswijk & Joris Schefold
 * Contact: michiel.boswijk@gmail.com
 * Last updated: 25-11-2015
 */

/* Reference package. */
package com.example.joris.sojo_racism;

/* Necessary imports. */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class ScoreExplanation extends DialogFragment {

    /* Method called on creation of the dialog. */
    @Override
    public Dialog onCreateDialog (Bundle SaveInstanceState) {

        /* Get context from current activity.*/
        Context context = getActivity();

        /* Create new dialog, and set message. */
        AlertDialog.Builder ackAlert = new AlertDialog.Builder(context);
        String message = getString(R.string.msg_score_explanation);
        ackAlert.setMessage(message);

        /* Create button in dialog. */
        ackAlert.setNeutralButton(R.string.btn_got_it, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        /* Set title and show dialog. */
        ackAlert.setTitle("Am I a racist?");
        ackAlert.create();

        return ackAlert.create();
    }
}