package com.ermile.common.networking.dns;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/ermile/common/networking/dns/MultiDns;", "Lokhttp3/Dns;", "servers", "", "(Ljava/util/List;)V", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "networking"})
public final class MultiDns implements okhttp3.Dns {
    private final java.util.List<okhttp3.Dns> servers = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<java.net.InetAddress> lookup(@org.jetbrains.annotations.NotNull()
    java.lang.String hostname) {
        return null;
    }
    
    public MultiDns(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends okhttp3.Dns> servers) {
        super();
    }
}