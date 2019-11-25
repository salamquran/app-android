package com.ermile.salamquran.android.component.activity;

import com.ermile.salamquran.android.component.fragment.QuranPageComponent;
import com.ermile.salamquran.android.di.ActivityScope;
import com.ermile.salamquran.android.module.activity.PagerActivityModule;
import com.ermile.salamquran.android.ui.PagerActivity;
import com.ermile.salamquran.android.ui.fragment.AyahTranslationFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = PagerActivityModule.class)
public interface PagerActivityComponent {
  // subcomponents
  QuranPageComponent.Builder quranPageComponentBuilder();

  void inject(PagerActivity pagerActivity);
  void inject(AyahTranslationFragment ayahTranslationFragment);

  @Subcomponent.Builder interface Builder {
    Builder withPagerActivityModule(PagerActivityModule pagerModule);
    PagerActivityComponent build();
  }
}
