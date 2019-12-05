package com.example.greenlightcasestudyapp.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.greenlightcasestudyapp.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackbarUtils {

    public static void showSnackbar(View v, String snackbarText) {
        if (v == null || snackbarText == null) {
            return;
        }
        Snackbar.make(v, snackbarText, Snackbar.LENGTH_LONG).show();
    }

    //show snack bar when connectivity changes
    public static void notifyInternet(int state, Context context, View v) {
        if (state > 0 ) {
            if (Constants.internetNotConnected) {

                Snackbar snackbar = Snackbar.make(v, Constants.INTERNET_CONNECTED, Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(context.getColor(R.color.colorSnackBarGreen));
                snackBarView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                TextView txtv = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
                txtv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                snackbar.show();
                Constants.internetNotConnected=false;
            }else{
                Constants.internetNotConnected=false;
            }
        }
        else {

            Snackbar snackbar = Snackbar.make(v, Constants.INTERNET_DISCONNECTED, Snackbar.LENGTH_INDEFINITE);
            View snackBarView= snackbar.getView();

            snackBarView.setBackgroundColor(context.getColor(R.color.colorSnackBarRed));
            snackBarView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            TextView txtv = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
            txtv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        }
    }
}
