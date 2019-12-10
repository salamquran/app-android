package com.salamquran.android.presenter.audio;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J(\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J\u0006\u0010#\u001a\u00020\u000eJ\u0006\u0010$\u001a\u00020\u000eJ\u000e\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000bJ>\u0010%\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u001eJ\u0010\u0010,\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0002X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/salamquran/android/presenter/audio/AudioPresenter;", "Lcom/salamquran/android/presenter/Presenter;", "Lcom/salamquran/android/ui/PagerActivity;", "quranInfo", "Lcom/salamquran/android/data/QuranInfo;", "audioUtil", "Lcom/salamquran/android/util/AudioUtils;", "quranFileUtils", "Lcom/salamquran/android/util/QuranFileUtils;", "(Lcom/salamquran/android/data/QuranInfo;Lcom/salamquran/android/util/AudioUtils;Lcom/salamquran/android/util/QuranFileUtils;)V", "lastAudioRequest", "Lcom/salamquran/android/dao/audio/AudioRequest;", "pagerActivity", "bind", "", "what", "getDownloadIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "request", "url", "", "destination", "title", "getLocalAudioPathInfo", "Lcom/salamquran/android/dao/audio/AudioPathInfo;", "qari", "Lcom/salamquran/android/common/QariItem;", "haveAllFiles", "", "audioPathInfo", "start", "Lcom/salamquran/android/data/SuraAyah;", "end", "onDownloadPermissionGranted", "onDownloadSuccess", "play", "audioRequest", "verseRepeat", "", "rangeRepeat", "enforceRange", "shouldStream", "unbind", "app_madaniBeta"})
public final class AudioPresenter implements com.salamquran.android.presenter.Presenter<com.salamquran.android.ui.PagerActivity> {
    private com.salamquran.android.ui.PagerActivity pagerActivity;
    private com.salamquran.android.dao.audio.AudioRequest lastAudioRequest;
    private final com.salamquran.android.data.QuranInfo quranInfo = null;
    private final com.salamquran.android.util.AudioUtils audioUtil = null;
    private final com.salamquran.android.util.QuranFileUtils quranFileUtils = null;
    
    public final void play(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah start, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah end, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QariItem qari, int verseRepeat, int rangeRepeat, boolean enforceRange, boolean shouldStream) {
    }
    
    public final void play(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.dao.audio.AudioRequest audioRequest) {
    }
    
    public final void onDownloadPermissionGranted() {
    }
    
    public final void onDownloadSuccess() {
    }
    
    private final android.content.Intent getDownloadIntent(android.content.Context context, com.salamquran.android.dao.audio.AudioRequest request) {
        return null;
    }
    
    private final android.content.Intent getDownloadIntent(android.content.Context context, java.lang.String url, java.lang.String destination, java.lang.String title) {
        return null;
    }
    
    private final com.salamquran.android.dao.audio.AudioPathInfo getLocalAudioPathInfo(com.salamquran.android.common.QariItem qari) {
        return null;
    }
    
    private final boolean haveAllFiles(com.salamquran.android.dao.audio.AudioPathInfo audioPathInfo, com.salamquran.android.data.SuraAyah start, com.salamquran.android.data.SuraAyah end) {
        return false;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.ui.PagerActivity what) {
    }
    
    @java.lang.Override()
    public void unbind(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.ui.PagerActivity what) {
    }
    
    @javax.inject.Inject()
    public AudioPresenter(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.QuranInfo quranInfo, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.AudioUtils audioUtil, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.QuranFileUtils quranFileUtils) {
        super();
    }
}