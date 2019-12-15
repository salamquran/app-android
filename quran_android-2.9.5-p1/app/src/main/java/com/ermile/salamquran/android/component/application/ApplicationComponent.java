package com.ermile.salamquran.android.component.application;

import com.ermile.data.page.provider.QuranPageModule;
import com.ermile.salamquran.android.QuranDataActivity;
import com.ermile.salamquran.android.QuranForwarderActivity;
import com.ermile.salamquran.android.QuranImportActivity;
import com.ermile.salamquran.android.SearchActivity;
import com.ermile.salamquran.android.component.activity.PagerActivityComponent;
import com.ermile.salamquran.android.data.QuranDataModule;
import com.ermile.salamquran.android.data.QuranDataProvider;
import com.ermile.salamquran.android.module.application.ApplicationModule;
import com.ermile.salamquran.android.module.application.DatabaseModule;
import com.ermile.common.networking.NetworkModule;
import com.ermile.salamquran.android.pageselect.PageSelectActivity;
import com.ermile.salamquran.android.service.AudioService;
import com.ermile.salamquran.android.service.QuranDownloadService;
import com.ermile.salamquran.android.ui.AudioManagerActivity;
import com.ermile.salamquran.android.ui.QuranActivity;
import com.ermile.salamquran.android.ui.TranslationManagerActivity;
import com.ermile.salamquran.android.ui.fragment.AddTagDialog;
import com.ermile.salamquran.android.ui.fragment.AyahPlaybackFragment;
import com.ermile.salamquran.android.ui.fragment.BookmarksFragment;
import com.ermile.salamquran.android.ui.fragment.JumpFragment;
import com.ermile.salamquran.android.ui.fragment.JuzListFragment;
import com.ermile.salamquran.android.ui.fragment.QuranAdvancedSettingsFragment;
import com.ermile.salamquran.android.ui.fragment.QuranSettingsFragment;
import com.ermile.salamquran.android.ui.fragment.SuraListFragment;
import com.ermile.salamquran.android.ui.fragment.TagBookmarkDialog;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    ApplicationModule.class,
    DatabaseModule.class,
    NetworkModule.class,
    QuranDataModule.class,
    QuranPageModule.class } )
public interface ApplicationComponent {
  // subcomponents
  PagerActivityComponent.Builder pagerActivityComponentBuilder();

  // content provider
  void inject(QuranDataProvider quranDataProvider);

  // services
  void inject(AudioService audioService);
  void inject(QuranDownloadService quranDownloadService);

  // activities
  void inject(QuranActivity quranActivity);
  void inject(QuranDataActivity quranDataActivity);
  void inject(QuranImportActivity quranImportActivity);
  void inject(AudioManagerActivity audioManagerActivity);
  void inject(QuranForwarderActivity quranForwarderActivity);
  void inject(SearchActivity searchActivity);
  void inject(PageSelectActivity pageSelectActivity);

  // fragments
  void inject(BookmarksFragment bookmarksFragment);
  void inject(QuranSettingsFragment fragment);
  void inject(TranslationManagerActivity translationManagerActivity);
  void inject(QuranAdvancedSettingsFragment quranAdvancedSettingsFragment);
  void inject(SuraListFragment suraListFragment);
  void inject(JuzListFragment juzListFragment);
  void inject(AyahPlaybackFragment ayahPlaybackFragment);
  void inject(JumpFragment jumpFragment);

  // dialogs
  void inject(TagBookmarkDialog tagBookmarkDialog);
  void inject(AddTagDialog addTagDialog);
}
