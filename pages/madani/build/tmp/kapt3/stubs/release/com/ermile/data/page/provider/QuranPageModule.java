package com.ermile.data.page.provider;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\b\u0010\u0006\u001a\u00020\u0007H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/ermile/data/page/provider/QuranPageModule;", "", "()V", "provideImageDrawHelpers", "", "Lcom/ermile/page/common/draw/ImageDrawHelper;", "provideMadaniPageSet", "Lcom/ermile/data/source/PageProvider;", "madani_release"})
@dagger.Module()
public final class QuranPageModule {
    public static final com.ermile.data.page.provider.QuranPageModule INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    @dagger.multibindings.StringKey(value = "madani")
    @dagger.multibindings.IntoMap()
    @dagger.Provides()
    public static final com.ermile.data.source.PageProvider provideMadaniPageSet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.multibindings.ElementsIntoSet()
    @dagger.Provides()
    public static final java.util.Set<com.ermile.page.common.draw.ImageDrawHelper> provideImageDrawHelpers() {
        return null;
    }
    
    private QuranPageModule() {
        super();
    }
}