package com.ermile.common.networking.dns;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Cache;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://google.github.io/dagger"
)
public final class DnsModule_ProvideDnsCacheFactory implements Factory<Cache> {
  private final DnsModule module;

  private final Provider<File> cacheDirectoryProvider;

  public DnsModule_ProvideDnsCacheFactory(DnsModule module, Provider<File> cacheDirectoryProvider) {
    this.module = module;
    this.cacheDirectoryProvider = cacheDirectoryProvider;
  }

  @Override
  public Cache get() {
    return proxyProvideDnsCache(module, cacheDirectoryProvider.get());
  }

  public static DnsModule_ProvideDnsCacheFactory create(DnsModule module,
      Provider<File> cacheDirectoryProvider) {
    return new DnsModule_ProvideDnsCacheFactory(module, cacheDirectoryProvider);
  }

  public static Cache proxyProvideDnsCache(DnsModule instance, File cacheDirectory) {
    return Preconditions.checkNotNull(instance.provideDnsCache(cacheDirectory), "Cannot return null from a non-@Nullable @Provides method");}
}
