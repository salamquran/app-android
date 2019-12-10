package com.ermile.common.networking.dns;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Dns;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://google.github.io/dagger"
)
public final class DnsModule_ProvidesDnsFactory implements Factory<Dns> {
  private final DnsModule module;

  private final Provider<List<Dns>> serversProvider;

  public DnsModule_ProvidesDnsFactory(DnsModule module, Provider<List<Dns>> serversProvider) {
    this.module = module;
    this.serversProvider = serversProvider;
  }

  @Override
  public Dns get() {
    return proxyProvidesDns(module, serversProvider.get());
  }

  public static DnsModule_ProvidesDnsFactory create(DnsModule module,
      Provider<List<Dns>> serversProvider) {
    return new DnsModule_ProvidesDnsFactory(module, serversProvider);
  }

  public static Dns proxyProvidesDns(DnsModule instance, List<Dns> servers) {
    return Preconditions.checkNotNull(instance.providesDns(servers), "Cannot return null from a non-@Nullable @Provides method");}
}
