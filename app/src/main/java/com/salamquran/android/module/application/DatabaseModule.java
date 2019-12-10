package com.salamquran.android.module.application;

import android.content.Context;

import com.salamquran.android.data.QuranInfo;
import com.salamquran.android.database.BookmarksDBAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

  @Provides
  @Singleton
  static BookmarksDBAdapter provideBookmarkDatabaseAdapter(Context context, QuranInfo quranInfo) {
    return new BookmarksDBAdapter(context, quranInfo.getNumberOfPages());
  }
}
