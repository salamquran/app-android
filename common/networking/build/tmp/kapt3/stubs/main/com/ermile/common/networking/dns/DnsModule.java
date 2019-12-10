package com.ermile.common.networking.dns;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007J\u001b\u0010\u000e\u001a\u00020\b2\u0011\u0010\u000f\u001a\r\u0012\t\u0012\u00070\b\u00a2\u0006\u0002\b\u00100\fH\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/ermile/common/networking/dns/DnsModule;", "", "()V", "provideDnsCache", "Lokhttp3/Cache;", "cacheDirectory", "Ljava/io/File;", "provideGoogleDns", "Lokhttp3/Dns;", "bootstrapClient", "Lokhttp3/OkHttpClient;", "provideServers", "", "dnsCache", "providesDns", "servers", "Lkotlin/jvm/JvmSuppressWildcards;", "networking"})
@dagger.Module()
public final class DnsModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final okhttp3.Dns providesDns(@org.jetbrains.annotations.NotNull()
    java.util.List<okhttp3.Dns> servers) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final okhttp3.Cache provideDnsCache(@org.jetbrains.annotations.NotNull()
    java.io.File cacheDirectory) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final java.util.List<okhttp3.Dns> provideServers(@org.jetbrains.annotations.NotNull()
    okhttp3.Cache dnsCache) {
        return null;
    }
    
    private final okhttp3.Dns provideGoogleDns(okhttp3.OkHttpClient bootstrapClient) {
        return null;
    }
    
    public DnsModule() {
        super();
    }
}