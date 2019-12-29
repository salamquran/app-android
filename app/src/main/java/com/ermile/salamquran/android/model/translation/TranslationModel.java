package com.ermile.salamquran.android.model.translation;

import android.content.Context;

import com.ermile.salamquran.android.common.QuranText;
import com.ermile.salamquran.android.data.QuranDataProvider;
import com.ermile.salamquran.android.data.VerseRange;
import com.ermile.salamquran.android.database.DatabaseHandler;
import com.ermile.salamquran.android.di.ActivityScope;
import com.ermile.salamquran.android.util.QuranFileUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

@ActivityScope
public class TranslationModel {
  private final Context appContext;
  private final QuranFileUtils quranFileUtils;

  @Inject
  TranslationModel(Context appContext, QuranFileUtils quranFileUtils) {
    this.appContext = appContext;
    this.quranFileUtils = quranFileUtils;
  }

  public Single<List<QuranText>> getArabicFromDatabase(VerseRange verses) {
    return getVersesFromDatabase(verses,
        QuranDataProvider.QURAN_ARABIC_DATABASE, DatabaseHandler.TextType.ARABIC);
  }

  public Single<List<QuranText>> getTranslationFromDatabase(VerseRange verses, String db) {
    return getVersesFromDatabase(verses, db, DatabaseHandler.TextType.TRANSLATION);
  }

  private Single<List<QuranText>> getVersesFromDatabase(VerseRange verses,
                                                        String database,
                                                        @DatabaseHandler.TextType int type) {
    return Single.fromCallable(() -> {
      DatabaseHandler databaseHandler =
          DatabaseHandler.getDatabaseHandler(appContext, database, quranFileUtils);
      return databaseHandler.getVerses(verses, type);
    });
  }
}
