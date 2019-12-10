package com.salamquran.android.ui.translation;

import com.salamquran.android.common.LocalTranslation;
import com.salamquran.android.common.QuranAyahInfo;

public interface OnTranslationActionListener {
  void onTranslationAction(QuranAyahInfo ayah, LocalTranslation[] translations, int actionId);
}
