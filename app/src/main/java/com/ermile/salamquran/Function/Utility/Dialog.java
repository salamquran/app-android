package com.ermile.salamquran.Function.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

public class Dialog {
    Context context;

    public Dialog(final Context context, String title, String desc, String btnTitle, boolean Cancelable, final Intent intent) {
        this.context = context;
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        /*Title*/
        builderSingle.setTitle(title);
        /*Message*/
        builderSingle.setMessage(desc);
        /*Button*/
        builderSingle.setPositiveButton(btnTitle,
                /*Open Url*/
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity)context).finish();
                        context.startActivity(intent);
                    }
                });
        builderSingle.setCancelable(Cancelable);
        builderSingle.show();
    }
}
