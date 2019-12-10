// Generated by Dagger (https://google.github.io/dagger).
package com.salamquran.android.module.application;

import com.ermile.data.source.DisplaySize;
import com.ermile.data.source.PageProvider;
import com.ermile.data.source.PageSizeCalculator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideQuranPageSizeCalculatorFactory implements Factory<PageSizeCalculator> {
  private final ApplicationModule module;

  private final Provider<PageProvider> pageProvider;

  private final Provider<DisplaySize> displaySizeProvider;

  public ApplicationModule_ProvideQuranPageSizeCalculatorFactory(ApplicationModule module,
      Provider<PageProvider> pageProvider, Provider<DisplaySize> displaySizeProvider) {
    this.module = module;
    this.pageProvider = pageProvider;
    this.displaySizeProvider = displaySizeProvider;
  }

  @Override
  public PageSizeCalculator get() {
    return proxyProvideQuranPageSizeCalculator(module, pageProvider.get(), displaySizeProvider.get());
  }

  public static ApplicationModule_ProvideQuranPageSizeCalculatorFactory create(
      ApplicationModule module, Provider<PageProvider> pageProvider,
      Provider<DisplaySize> displaySizeProvider) {
    return new ApplicationModule_ProvideQuranPageSizeCalculatorFactory(module, pageProvider, displaySizeProvider);
  }

  public static PageSizeCalculator proxyProvideQuranPageSizeCalculator(ApplicationModule instance,
      PageProvider pageProvider, DisplaySize displaySize) {
    return Preconditions.checkNotNull(instance.provideQuranPageSizeCalculator(pageProvider, displaySize), "Cannot return null from a non-@Nullable @Provides method");}
}
