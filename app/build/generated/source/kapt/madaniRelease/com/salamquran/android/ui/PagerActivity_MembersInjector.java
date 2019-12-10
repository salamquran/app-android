// Generated by Dagger (https://google.github.io/dagger).
package com.salamquran.android.ui;

import com.salamquran.android.data.QuranInfo;
import com.salamquran.android.database.TranslationsDBAdapter;
import com.salamquran.android.model.bookmark.BookmarkModel;
import com.salamquran.android.model.translation.ArabicDatabaseUtils;
import com.salamquran.android.presenter.audio.AudioPresenter;
import com.salamquran.android.presenter.bookmark.RecentPagePresenter;
import com.salamquran.android.util.AudioUtils;
import com.salamquran.android.util.QuranAppUtils;
import com.salamquran.android.util.QuranFileUtils;
import com.salamquran.android.util.QuranScreenInfo;
import com.salamquran.android.util.QuranSettings;
import com.salamquran.android.util.ShareUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PagerActivity_MembersInjector implements MembersInjector<PagerActivity> {
  private final Provider<BookmarkModel> bookmarkModelProvider;

  private final Provider<RecentPagePresenter> recentPagePresenterProvider;

  private final Provider<QuranSettings> quranSettingsProvider;

  private final Provider<QuranScreenInfo> quranScreenInfoProvider;

  private final Provider<ArabicDatabaseUtils> arabicDatabaseUtilsProvider;

  private final Provider<TranslationsDBAdapter> translationsDBAdapterProvider;

  private final Provider<QuranAppUtils> quranAppUtilsProvider;

  private final Provider<ShareUtil> shareUtilProvider;

  private final Provider<AudioUtils> audioUtilsProvider;

  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  private final Provider<AudioPresenter> audioPresenterProvider;

  public PagerActivity_MembersInjector(Provider<BookmarkModel> bookmarkModelProvider,
      Provider<RecentPagePresenter> recentPagePresenterProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<QuranScreenInfo> quranScreenInfoProvider,
      Provider<ArabicDatabaseUtils> arabicDatabaseUtilsProvider,
      Provider<TranslationsDBAdapter> translationsDBAdapterProvider,
      Provider<QuranAppUtils> quranAppUtilsProvider, Provider<ShareUtil> shareUtilProvider,
      Provider<AudioUtils> audioUtilsProvider, Provider<QuranInfo> quranInfoProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider,
      Provider<AudioPresenter> audioPresenterProvider) {
    this.bookmarkModelProvider = bookmarkModelProvider;
    this.recentPagePresenterProvider = recentPagePresenterProvider;
    this.quranSettingsProvider = quranSettingsProvider;
    this.quranScreenInfoProvider = quranScreenInfoProvider;
    this.arabicDatabaseUtilsProvider = arabicDatabaseUtilsProvider;
    this.translationsDBAdapterProvider = translationsDBAdapterProvider;
    this.quranAppUtilsProvider = quranAppUtilsProvider;
    this.shareUtilProvider = shareUtilProvider;
    this.audioUtilsProvider = audioUtilsProvider;
    this.quranInfoProvider = quranInfoProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
    this.audioPresenterProvider = audioPresenterProvider;
  }

  public static MembersInjector<PagerActivity> create(Provider<BookmarkModel> bookmarkModelProvider,
      Provider<RecentPagePresenter> recentPagePresenterProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<QuranScreenInfo> quranScreenInfoProvider,
      Provider<ArabicDatabaseUtils> arabicDatabaseUtilsProvider,
      Provider<TranslationsDBAdapter> translationsDBAdapterProvider,
      Provider<QuranAppUtils> quranAppUtilsProvider, Provider<ShareUtil> shareUtilProvider,
      Provider<AudioUtils> audioUtilsProvider, Provider<QuranInfo> quranInfoProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider,
      Provider<AudioPresenter> audioPresenterProvider) {
    return new PagerActivity_MembersInjector(bookmarkModelProvider, recentPagePresenterProvider, quranSettingsProvider, quranScreenInfoProvider, arabicDatabaseUtilsProvider, translationsDBAdapterProvider, quranAppUtilsProvider, shareUtilProvider, audioUtilsProvider, quranInfoProvider, quranFileUtilsProvider, audioPresenterProvider);}

  @Override
  public void injectMembers(PagerActivity instance) {
    injectBookmarkModel(instance, bookmarkModelProvider.get());
    injectRecentPagePresenter(instance, recentPagePresenterProvider.get());
    injectQuranSettings(instance, quranSettingsProvider.get());
    injectQuranScreenInfo(instance, quranScreenInfoProvider.get());
    injectArabicDatabaseUtils(instance, arabicDatabaseUtilsProvider.get());
    injectTranslationsDBAdapter(instance, translationsDBAdapterProvider.get());
    injectQuranAppUtils(instance, quranAppUtilsProvider.get());
    injectShareUtil(instance, shareUtilProvider.get());
    injectAudioUtils(instance, audioUtilsProvider.get());
    injectQuranInfo(instance, quranInfoProvider.get());
    injectQuranFileUtils(instance, quranFileUtilsProvider.get());
    injectAudioPresenter(instance, audioPresenterProvider.get());
  }

  public static void injectBookmarkModel(PagerActivity instance, BookmarkModel bookmarkModel) {
    instance.bookmarkModel = bookmarkModel;
  }

  public static void injectRecentPagePresenter(PagerActivity instance,
      RecentPagePresenter recentPagePresenter) {
    instance.recentPagePresenter = recentPagePresenter;
  }

  public static void injectQuranSettings(PagerActivity instance, QuranSettings quranSettings) {
    instance.quranSettings = quranSettings;
  }

  public static void injectQuranScreenInfo(PagerActivity instance,
      QuranScreenInfo quranScreenInfo) {
    instance.quranScreenInfo = quranScreenInfo;
  }

  public static void injectArabicDatabaseUtils(PagerActivity instance,
      ArabicDatabaseUtils arabicDatabaseUtils) {
    instance.arabicDatabaseUtils = arabicDatabaseUtils;
  }

  public static void injectTranslationsDBAdapter(PagerActivity instance,
      TranslationsDBAdapter translationsDBAdapter) {
    instance.translationsDBAdapter = translationsDBAdapter;
  }

  public static void injectQuranAppUtils(PagerActivity instance, QuranAppUtils quranAppUtils) {
    instance.quranAppUtils = quranAppUtils;
  }

  public static void injectShareUtil(PagerActivity instance, ShareUtil shareUtil) {
    instance.shareUtil = shareUtil;
  }

  public static void injectAudioUtils(PagerActivity instance, AudioUtils audioUtils) {
    instance.audioUtils = audioUtils;
  }

  public static void injectQuranInfo(PagerActivity instance, QuranInfo quranInfo) {
    instance.quranInfo = quranInfo;
  }

  public static void injectQuranFileUtils(PagerActivity instance, QuranFileUtils quranFileUtils) {
    instance.quranFileUtils = quranFileUtils;
  }

  public static void injectAudioPresenter(PagerActivity instance, AudioPresenter audioPresenter) {
    instance.audioPresenter = audioPresenter;
  }
}
