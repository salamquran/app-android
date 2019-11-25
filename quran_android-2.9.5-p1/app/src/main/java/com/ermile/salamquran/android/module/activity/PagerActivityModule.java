package com.ermile.salamquran.android.module.activity;

import android.content.Context;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.data.QuranInfo;
import com.ermile.salamquran.android.di.ActivityScope;
import com.ermile.salamquran.android.ui.PagerActivity;
import com.ermile.salamquran.android.ui.helpers.AyahSelectedListener;
import com.ermile.salamquran.android.util.QuranScreenInfo;
import com.ermile.salamquran.android.util.QuranUtils;
import com.ermile.salamquran.android.util.TranslationUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class PagerActivityModule {
  private final PagerActivity pagerActivity;

  public PagerActivityModule(PagerActivity pagerActivity) {
    this.pagerActivity = pagerActivity;
  }

  @Provides
  AyahSelectedListener provideAyahSelectedListener() {
    return this.pagerActivity;
  }

  @Provides
  @ActivityScope
  String provideImageWidth(QuranScreenInfo screenInfo) {
    return QuranUtils.isDualPages(pagerActivity, screenInfo) ?
        screenInfo.getTabletWidthParam() : screenInfo.getWidthParam();
  }

  @Provides
  @ActivityScope
  TranslationUtil provideTranslationUtil(Context context, QuranInfo quranInfo) {
    return new TranslationUtil(
        context.getResources().getColor(R.color.translation_translator_color),
        quranInfo);
  }
}
