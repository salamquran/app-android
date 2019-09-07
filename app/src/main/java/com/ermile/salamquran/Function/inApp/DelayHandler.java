package com.ermile.salamquran.Function.inApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class DelayHandler {
    public DelayHandler(final Context context, Integer Delay_sec, final Intent intent) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        }, Delay_sec*1000);
    }
}
