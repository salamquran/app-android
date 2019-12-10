package com.ermile.common.networking.dns;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lcom/ermile/common/networking/dns/DnsFallback;", "Lokhttp3/Dns;", "()V", "initialized", "", "getInitialized", "()Z", "setInitialized", "(Z)V", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "networking"})
public final class DnsFallback implements okhttp3.Dns {
    private boolean initialized;
    
    public final boolean getInitialized() {
        return false;
    }
    
    public final void setInitialized(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<java.net.InetAddress> lookup(@org.jetbrains.annotations.NotNull()
    java.lang.String hostname) {
        return null;
    }
    
    public DnsFallback() {
        super();
    }
}