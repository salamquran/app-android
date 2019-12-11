// Generated by Dagger (https://google.github.io/dagger).
package com.ermile.data.page.provider;

import com.ermile.page.common.draw.ImageDrawHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;

public final class QuranPageModule_ProvideImageDrawHelpersFactory implements Factory<Set<ImageDrawHelper>> {
  private static final QuranPageModule_ProvideImageDrawHelpersFactory INSTANCE = new QuranPageModule_ProvideImageDrawHelpersFactory();

  @Override
  public Set<ImageDrawHelper> get() {
    return proxyProvideImageDrawHelpers();
  }

  public static QuranPageModule_ProvideImageDrawHelpersFactory create() {
    return INSTANCE;
  }

  public static Set<ImageDrawHelper> proxyProvideImageDrawHelpers() {
    return Preconditions.checkNotNull(QuranPageModule.provideImageDrawHelpers(), "Cannot return null from a non-@Nullable @Provides method");}
}