package com.ermile.common.networking.dns;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.Dns;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://google.github.io/dagger"
)
public final class DnsModule_ProvideServersFactory implements Factory<List<Dns>> {
  private final DnsModule module;

  private final Provider<Cache> dnsCacheProvider;

  public DnsModule_ProvideServersFactory(DnsModule module, Provider<Cache> dnsCacheProvider) {
    this.module = module;
    this.dnsCacheProvider = dnsCacheProvider;
  }

  @Override
  public List<Dns> get() {
    return proxyProvideServers(module, dnsCacheProvider.get());
  }

  public static DnsModule_ProvideServersFactory create(DnsModule module,
      Provider<Cache> dnsCacheProvider) {
    return new DnsModule_ProvideServersFactory(module, dnsCacheProvider);
  }

  public static List<Dns> proxyProvideServers(DnsModule instance, Cache dnsCache) {
    return Preconditions.checkNotNull(instance.provideServers(dnsCache), "Cannot return null from a non-@Nullable @Provides method");}
}
