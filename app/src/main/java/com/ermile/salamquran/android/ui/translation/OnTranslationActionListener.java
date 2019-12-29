package com.ermile.salamquran.android.ui.translation;

import com.ermile.salamquran.android.common.LocalTranslation;
import com.ermile.salamquran.android.common.QuranAyahInfo;

public interface OnTranslationActionListener {
  void onTranslationAction(QuranAyahInfo ayah, LocalTranslation[] translations, int actionId);
}
