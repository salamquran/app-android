package com.ermile.salamquran.android.salamquran.Utility;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class ColorUtil {

    public static void setGradient(View view, String START , String END){

        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {
                        Color.parseColor(START),
                        Color.parseColor(END)
                });
        view.setBackgroundDrawable(gradient);
    }
}
