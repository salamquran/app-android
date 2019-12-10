package com.salamquran.android.component.application;

import com.ermile.data.page.provider.QuranPageModule;
import com.salamquran.android.QuranDataActivity;
import com.salamquran.android.QuranForwarderActivity;
import com.salamquran.android.QuranImportActivity;
import com.salamquran.android.SearchActivity;
import com.salamquran.android.component.activity.PagerActivityComponent;
import com.salamquran.android.data.QuranDataModule;
import com.salamquran.android.data.QuranDataProvider;
import com.salamquran.android.module.application.ApplicationModule;
import com.salamquran.android.module.application.DatabaseModule;
import com.ermile.common.networking.NetworkModule;
import com.salamquran.android.pageselect.PageSelectActivity;
import com.salamquran.android.service.AudioService;
import com.salamquran.android.service.QuranDownloadService;
import com.salamquran.android.ui.AudioManagerActivity;
import com.salamquran.android.ui.QuranActivity;
import com.salamquran.android.ui.TranslationManagerActivity;
import com.salamquran.android.ui.fragment.AddTagDialog;
import com.salamquran.android.ui.fragment.AyahPlaybackFragment;
import com.salamquran.android.ui.fragment.BookmarksFragment;
import com.salamquran.android.ui.fragment.JumpFragment;
import com.salamquran.android.ui.fragment.JuzListFragment;
import com.salamquran.android.ui.fragment.QuranAdvancedSettingsFragment;
import com.salamquran.android.ui.fragment.QuranSettingsFragment;
import com.salamquran.android.ui.fragment.SuraListFragment;
import com.salamquran.android.ui.fragment.TagBookmarkDialog;

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
