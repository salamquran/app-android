package com.salamquran.android.component.activity;

import com.salamquran.android.component.fragment.QuranPageComponent;
import com.salamquran.android.di.ActivityScope;
import com.salamquran.android.module.activity.PagerActivityModule;
import com.salamquran.android.ui.PagerActivity;
import com.salamquran.android.ui.fragment.AyahTranslationFragment;

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
