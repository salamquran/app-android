package com.salamquran.android.module.activity;

import android.content.Context;

import com.salamquran.android.R;
import com.salamquran.android.data.QuranInfo;
import com.salamquran.android.di.ActivityScope;
import com.salamquran.android.ui.PagerActivity;
import com.salamquran.android.ui.helpers.AyahSelectedListener;
import com.salamquran.android.util.QuranScreenInfo;
import com.salamquran.android.util.QuranUtils;
import com.salamquran.android.util.TranslationUtil;

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
