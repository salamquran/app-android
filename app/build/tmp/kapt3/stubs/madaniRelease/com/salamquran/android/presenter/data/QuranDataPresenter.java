package com.salamquran.android.presenter.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00012B7\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190$2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010\'\u001a\u00020(2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\b\u0010)\u001a\u00020(H\u0007J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u0019H\u0003J\b\u0010,\u001a\u00020(H\u0003J\b\u0010-\u001a\u00020(H\u0002J\u0006\u0010.\u001a\u00020\u0014J\u0010\u0010/\u001a\u0002002\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u00101\u001a\u00020(2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0002X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\n \"*\u0004\u0018\u00010!0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/salamquran/android/presenter/data/QuranDataPresenter;", "Lcom/salamquran/android/presenter/Presenter;", "Lcom/salamquran/android/QuranDataActivity;", "appContext", "Landroid/content/Context;", "quranInfo", "Lcom/salamquran/android/data/QuranInfo;", "quranScreenInfo", "Lcom/salamquran/android/util/QuranScreenInfo;", "quranPageProvider", "Lcom/ermile/data/source/PageProvider;", "copyDatabaseUtil", "Lcom/salamquran/android/util/CopyDatabaseUtil;", "quranFileUtils", "Lcom/salamquran/android/util/QuranFileUtils;", "(Landroid/content/Context;Lcom/salamquran/android/data/QuranInfo;Lcom/salamquran/android/util/QuranScreenInfo;Lcom/ermile/data/source/PageProvider;Lcom/salamquran/android/util/CopyDatabaseUtil;Lcom/salamquran/android/util/QuranFileUtils;)V", "activity", "getAppContext", "()Landroid/content/Context;", "cachedPageType", "", "checkPagesDisposable", "Lio/reactivex/disposables/Disposable;", "debugLog", "lastCachedResult", "Lcom/salamquran/android/presenter/data/QuranDataPresenter$QuranDataStatus;", "getQuranFileUtils", "()Lcom/salamquran/android/util/QuranFileUtils;", "getQuranInfo", "()Lcom/salamquran/android/data/QuranInfo;", "getQuranScreenInfo", "()Lcom/salamquran/android/util/QuranScreenInfo;", "quranSettings", "Lcom/salamquran/android/util/QuranSettings;", "kotlin.jvm.PlatformType", "actuallyCheckPages", "Lio/reactivex/Single;", "totalPages", "", "bind", "", "checkPages", "checkPatchStatus", "quranDataStatus", "copyArabicDatabaseIfNecessary", "generateDebugLog", "getDebugLog", "supportLegacyPages", "Lio/reactivex/Completable;", "unbind", "QuranDataStatus", "app_madaniRelease"})
public final class QuranDataPresenter implements com.salamquran.android.presenter.Presenter<com.salamquran.android.QuranDataActivity> {
    private com.salamquran.android.QuranDataActivity activity;
    private io.reactivex.disposables.Disposable checkPagesDisposable;
    private java.lang.String cachedPageType;
    private com.salamquran.android.presenter.data.QuranDataPresenter.QuranDataStatus lastCachedResult;
    private java.lang.String debugLog;
    private final com.salamquran.android.util.QuranSettings quranSettings = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.NotNull()
    private final com.salamquran.android.data.QuranInfo quranInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.salamquran.android.util.QuranScreenInfo quranScreenInfo = null;
    private final com.ermile.data.source.PageProvider quranPageProvider = null;
    private final com.salamquran.android.util.CopyDatabaseUtil copyDatabaseUtil = null;
    @org.jetbrains.annotations.NotNull()
    private final com.salamquran.android.util.QuranFileUtils quranFileUtils = null;
    
    @androidx.annotation.UiThread()
    public final void checkPages() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDebugLog() {
        return null;
    }
    
    private final void generateDebugLog() {
    }
    
    private final io.reactivex.Completable supportLegacyPages(int totalPages) {
        return null;
    }
    
    private final io.reactivex.Single<com.salamquran.android.presenter.data.QuranDataPresenter.QuranDataStatus> actuallyCheckPages(int totalPages) {
        return null;
    }
    
    @androidx.annotation.WorkerThread()
    private final com.salamquran.android.presenter.data.QuranDataPresenter.QuranDataStatus checkPatchStatus(com.salamquran.android.presenter.data.QuranDataPresenter.QuranDataStatus quranDataStatus) {
        return null;
    }
    
    @androidx.annotation.WorkerThread()
    private final void copyArabicDatabaseIfNecessary() {
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.QuranDataActivity activity) {
    }
    
    @java.lang.Override()
    public void unbind(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.QuranDataActivity activity) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getAppContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.data.QuranInfo getQuranInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.util.QuranScreenInfo getQuranScreenInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.util.QuranFileUtils getQuranFileUtils() {
        return null;
    }
    
    @javax.inject.Inject()
    public QuranDataPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.QuranInfo quranInfo, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.QuranScreenInfo quranScreenInfo, @org.jetbrains.annotations.NotNull()
    com.ermile.data.source.PageProvider quranPageProvider, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.CopyDatabaseUtil copyDatabaseUtil, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.QuranFileUtils quranFileUtils) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J=\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001c\u001a\u00020\u0006J\u0006\u0010\u001d\u001a\u00020\u0006J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u001f"}, d2 = {"Lcom/salamquran/android/presenter/data/QuranDataPresenter$QuranDataStatus;", "", "portraitWidth", "", "landscapeWidth", "havePortrait", "", "haveLandscape", "patchParam", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;)V", "getHaveLandscape", "()Z", "getHavePortrait", "getLandscapeWidth", "()Ljava/lang/String;", "getPatchParam", "getPortraitWidth", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "havePages", "needLandscape", "needPortrait", "toString", "app_madaniRelease"})
    public static final class QuranDataStatus {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String portraitWidth = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String landscapeWidth = null;
        private final boolean havePortrait = false;
        private final boolean haveLandscape = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String patchParam = null;
        
        public final boolean needPortrait() {
            return false;
        }
        
        public final boolean needLandscape() {
            return false;
        }
        
        public final boolean havePages() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPortraitWidth() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLandscapeWidth() {
            return null;
        }
        
        public final boolean getHavePortrait() {
            return false;
        }
        
        public final boolean getHaveLandscape() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPatchParam() {
            return null;
        }
        
        public QuranDataStatus(@org.jetbrains.annotations.NotNull()
        java.lang.String portraitWidth, @org.jetbrains.annotations.NotNull()
        java.lang.String landscapeWidth, boolean havePortrait, boolean haveLandscape, @org.jetbrains.annotations.Nullable()
        java.lang.String patchParam) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.salamquran.android.presenter.data.QuranDataPresenter.QuranDataStatus copy(@org.jetbrains.annotations.NotNull()
        java.lang.String portraitWidth, @org.jetbrains.annotations.NotNull()
        java.lang.String landscapeWidth, boolean havePortrait, boolean haveLandscape, @org.jetbrains.annotations.Nullable()
        java.lang.String patchParam) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
}