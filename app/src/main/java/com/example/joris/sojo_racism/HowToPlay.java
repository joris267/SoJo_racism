/* HowToPlay Class
 * Class type: Dialog
 *
 * Class implements a dialog showing the information about how to play the game.
 *
 * Authors: Michiel Boswijk & Joris Schefold
 * Contact: michiel.boswijk@gmail.com
 * Last updated: 23-11-2015
 */

//TODO: Add some links to broader the that is the Implicit Association Test (like the ones Freddy posted on Slack).

/* Reference package. */
package com.example.joris.sojo_racism;

/* Necessary imports. */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class HowToPlay extends DialogFragment {

    /* Method called on creation of the dialog. */
    @Override
    public Dialog onCreateDialog (Bundle SaveInstanceState) {

        /* Get context from current activity.*/
        Context context = getActivity();

        /* Create new dialog, and set message. */
        AlertDialog.Builder ackAlert = new AlertDialog.Builder(context);
        String message = getString(R.string.msg_howtoplay);
        ackAlert.setMessage(message);

        /* Create button in dialog. */
        ackAlert.setNeutralButton(R.string.btn_got_it, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        /* Set title and show dialog. */
        ackAlert.setTitle("How does it work?");
        ackAlert.create();

        return ackAlert.create();
    }
}