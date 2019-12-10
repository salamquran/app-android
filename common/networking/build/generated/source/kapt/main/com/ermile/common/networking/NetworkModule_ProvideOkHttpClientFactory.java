package com.ermile.common.networking;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Dns;
import okhttp3.OkHttpClient;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://google.github.io/dagger"
)
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<Dns> dnsProvider;

  public NetworkModule_ProvideOkHttpClientFactory(Provider<Dns> dnsProvider) {
    this.dnsProvider = dnsProvider;
  }

  @Override
  public OkHttpClient get() {
    return proxyProvideOkHttpClient(dnsProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(Provider<Dns> dnsProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(dnsProvider);
  }

  public static OkHttpClient proxyProvideOkHttpClient(Dns dns) {
    return Preconditions.checkNotNull(NetworkModule.provideOkHttpClient(dns), "Cannot return null from a non-@Nullable @Provides method");}
}
