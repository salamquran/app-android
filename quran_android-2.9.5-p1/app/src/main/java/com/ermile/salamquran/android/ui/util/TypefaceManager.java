package com.ermile.salamquran.android.ui.util;

import android.content.Context;
import android.graphics.Typeface;

import com.ermile.salamquran.android.data.QuranFileConstants;

import androidx.annotation.NonNull;

public class TypefaceManager {
  public static final int TYPE_UTHMANI_HAFS = 1;
  public static final int TYPE_NOOR_HAYAH = 2;

  private static Typeface typeface;
  private static Typeface arabicTafseerTypeface;

  public static Typeface getUthmaniTypeface(@NonNull Context context) {
    if (typeface == null) {
      final String fontName;
      switch (QuranFileConstants.FONT_TYPE) {
        case TYPE_NOOR_HAYAH: {
          fontName = "dana_regular.ttf";
          break;
        }
        case TYPE_UTHMANI_HAFS:
        default: {
          fontName = "dana_regular.ttf";
        }
      }
      typeface = Typeface.createFromAsset(context.getAssets(), fontName);
    }
    return typeface;
  }

  public static Typeface getTafseerTypeface(@NonNull Context context) {
    if (arabicTafseerTypeface == null) {
      arabicTafseerTypeface = Typeface.createFromAsset(context.getAssets(), "dana_regular.ttf");
    }
    return arabicTafseerTypeface;
  }
}
