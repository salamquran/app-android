package com.ermile.salamquran.android.component.fragment;

import com.ermile.salamquran.android.di.QuranPageScope;
import com.ermile.salamquran.android.module.fragment.QuranPageModule;
import com.ermile.salamquran.android.ui.fragment.QuranPageFragment;
import com.ermile.salamquran.android.ui.fragment.TabletFragment;
import com.ermile.salamquran.android.ui.fragment.TranslationFragment;

import dagger.Subcomponent;

@QuranPageScope
@Subcomponent(modules = QuranPageModule.class)
public interface QuranPageComponent {
  void inject(QuranPageFragment quranPageFragment);
  void inject(TabletFragment tabletFragment);
  void inject(TranslationFragment translationFragment);

  @Subcomponent.Builder interface Builder {
    Builder withQuranPageModule(QuranPageModule quranPageModule);
    QuranPageComponent build();
  }
}
