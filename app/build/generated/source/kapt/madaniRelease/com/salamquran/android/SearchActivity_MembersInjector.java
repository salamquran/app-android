// Generated by Dagger (https://google.github.io/dagger).
package com.salamquran.android;

import com.salamquran.android.data.QuranInfo;
import com.salamquran.android.util.QuranFileUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SearchActivity_MembersInjector implements MembersInjector<SearchActivity> {
  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  public SearchActivity_MembersInjector(Provider<QuranInfo> quranInfoProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    this.quranInfoProvider = quranInfoProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
  }

  public static MembersInjector<SearchActivity> create(Provider<QuranInfo> quranInfoProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    return new SearchActivity_MembersInjector(quranInfoProvider, quranFileUtilsProvider);}

  @Override
  public void injectMembers(SearchActivity instance) {
    injectQuranInfo(instance, quranInfoProvider.get());
    injectQuranFileUtils(instance, quranFileUtilsProvider.get());
  }

  public static void injectQuranInfo(SearchActivity instance, QuranInfo quranInfo) {
    instance.quranInfo = quranInfo;
  }

  public static void injectQuranFileUtils(SearchActivity instance, QuranFileUtils quranFileUtils) {
    instance.quranFileUtils = quranFileUtils;
  }
}
