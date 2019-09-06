package com.ermile.salamquran.Function.inApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.ermile.salamquran.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBar {
    Context context;

    public SnackBar(final Context context,Integer ID, String Title, String Button,Integer Color, Integer Duration_sec, final Intent intent) {
        this.context = context;
        View view = new View(context).findViewById(ID);
        Snackbar snackbar = Snackbar.make(view, Title, Snackbar.LENGTH_INDEFINITE)
                .setAction(Button, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((Activity)context).finish();
                        context.startActivity(intent);
                    }
                });
        snackbar.setActionTextColor(Color);
        snackbar.setDuration(Duration_sec*1000);
        snackbar.show();
    }
}
