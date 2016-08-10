package com.talentica.presentation.utils;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ShaktiM on 20-01-2016.
 */
public class CustomSnackbar {
    private static String TAG = "CustomSnackbar";


    public static void show(Snackbar snackbar, Boolean redText) {

        Log.d(TAG, "makeCustomSnackbar with redText ?   " + redText);
        Snackbar customSnackbar = snackbar;
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(Color.parseColor("#00BE7D")); // snackbar background color
        TextView textView;
        if (redText) {
            textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.parseColor("#fa182a"));
            textView.setBackgroundColor(Color.parseColor("#00BE7D"));

        } // snackbar action text color

        else {
            textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.parseColor("#00BE7D"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        snackbar.show();
    }
}
