package com.ermile.salamquran.android.presenter.quran.ayahtracker;

import androidx.annotation.NonNull;

import com.ermile.salamquran.android.data.QuranInfo;
import com.ermile.salamquran.android.ui.helpers.HighlightType;
import com.ermile.salamquran.android.ui.translation.TranslationView;

public class AyahTranslationTrackerItem extends AyahTrackerItem<TranslationView> {
  private final QuranInfo quranInfo;

  public AyahTranslationTrackerItem(int page,
                                    QuranInfo quranInfo,
                                    @NonNull TranslationView ayahView) {
    super(page, ayahView);
    this.quranInfo = quranInfo;
  }

  @Override
  boolean onHighlightAyah(int page, int sura, int ayah, HighlightType type, boolean scrollToAyah) {
    if (this.page == page) {
      ayahView.highlightAyah(quranInfo.getAyahId(sura, ayah));
      return true;
    }
    ayahView.unhighlightAyat();
    return false;
  }

  @Override
  void onUnHighlightAyah(int page, int sura, int ayah, HighlightType type) {
    if (this.page == page) {
      ayahView.unhighlightAyat();
    }
  }

  @Override
  void onUnHighlightAyahType(HighlightType type) {
    ayahView.unhighlightAyat();
  }
}
