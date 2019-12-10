package com.salamquran.android.data;

import com.ermile.data.source.PageProvider;
import com.salamquran.android.util.QuranSettings;

import java.util.Map;

import dagger.Module;
import dagger.Provides;

@Module
public class QuranDataModule {

  @Provides
  static PageProvider provideQuranPageProvider(Map<String, PageProvider> providers,
                                               QuranSettings quranSettings) {
    final String key = quranSettings.getPageType();
    final String fallbackType = "madani";
    if (key == null) {
      quranSettings.setPageType(fallbackType);
    }
    return providers.get(key == null ? fallbackType : key);
  }
}
