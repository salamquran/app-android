// Generated by Dagger (https://google.github.io/dagger).
package com.salamquran.android.data;

import com.salamquran.android.database.TranslationsDBAdapter;
import com.salamquran.android.util.QuranFileUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class QuranDataProvider_MembersInjector implements MembersInjector<QuranDataProvider> {
  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<TranslationsDBAdapter> translationsDBAdapterProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  public QuranDataProvider_MembersInjector(Provider<QuranInfo> quranInfoProvider,
      Provider<TranslationsDBAdapter> translationsDBAdapterProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    this.quranInfoProvider = quranInfoProvider;
    this.translationsDBAdapterProvider = translationsDBAdapterProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
  }

  public static MembersInjector<QuranDataProvider> create(Provider<QuranInfo> quranInfoProvider,
      Provider<TranslationsDBAdapter> translationsDBAdapterProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    return new QuranDataProvider_MembersInjector(quranInfoProvider, translationsDBAdapterProvider, quranFileUtilsProvider);}

  @Override
  public void injectMembers(QuranDataProvider instance) {
    injectQuranInfo(instance, quranInfoProvider.get());
    injectTranslationsDBAdapter(instance, translationsDBAdapterProvider.get());
    injectQuranFileUtils(instance, quranFileUtilsProvider.get());
  }

  public static void injectQuranInfo(QuranDataProvider instance, QuranInfo quranInfo) {
    instance.quranInfo = quranInfo;
  }

  public static void injectTranslationsDBAdapter(QuranDataProvider instance,
      TranslationsDBAdapter translationsDBAdapter) {
    instance.translationsDBAdapter = translationsDBAdapter;
  }

  public static void injectQuranFileUtils(QuranDataProvider instance,
      QuranFileUtils quranFileUtils) {
    instance.quranFileUtils = quranFileUtils;
  }
}
