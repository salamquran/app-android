// Generated by Dagger (https://google.github.io/dagger).
package com.salamquran.android.module.activity;

import com.salamquran.android.util.QuranScreenInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class PagerActivityModule_ProvideImageWidthFactory implements Factory<String> {
  private final PagerActivityModule module;

  private final Provider<QuranScreenInfo> screenInfoProvider;

  public PagerActivityModule_ProvideImageWidthFactory(PagerActivityModule module,
      Provider<QuranScreenInfo> screenInfoProvider) {
    this.module = module;
    this.screenInfoProvider = screenInfoProvider;
  }

  @Override
  public String get() {
    return proxyProvideImageWidth(module, screenInfoProvider.get());
  }

  public static PagerActivityModule_ProvideImageWidthFactory create(PagerActivityModule module,
      Provider<QuranScreenInfo> screenInfoProvider) {
    return new PagerActivityModule_ProvideImageWidthFactory(module, screenInfoProvider);
  }

  public static String proxyProvideImageWidth(PagerActivityModule instance,
      QuranScreenInfo screenInfo) {
    return Preconditions.checkNotNull(instance.provideImageWidth(screenInfo), "Cannot return null from a non-@Nullable @Provides method");}
}
