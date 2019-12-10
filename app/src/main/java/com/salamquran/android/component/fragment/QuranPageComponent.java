package com.salamquran.android.component.fragment;

import com.salamquran.android.di.QuranPageScope;
import com.salamquran.android.module.fragment.QuranPageModule;
import com.salamquran.android.ui.fragment.QuranPageFragment;
import com.salamquran.android.ui.fragment.TabletFragment;
import com.salamquran.android.ui.fragment.TranslationFragment;

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
