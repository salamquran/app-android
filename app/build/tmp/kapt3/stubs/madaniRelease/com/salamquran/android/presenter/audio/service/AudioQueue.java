package com.salamquran.android.presenter.audio.service;

import java.lang.System;

/**
 * * This class maintains a virtual audio queue for playback.
 * * Given an [AudioRequest], this class maintains the queue for this request,
 * * supporting operations such as switching to the next or previous ayahs or
 * * jumping to an ayah. This class doesn't do the actual playback, but it
 * * dictates what to play (while respecting repeat settings) and where to
 * * find it.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0002J \u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u0010J\u0010\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0010J\u0010\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0010J\u0018\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\f\u0010\u001b\u001a\u00020\u001c*\u00020\u001cH\u0002J\f\u0010\u001d\u001a\u00020\u001c*\u00020\u001cH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/salamquran/android/presenter/audio/service/AudioQueue;", "", "quranInfo", "Lcom/salamquran/android/data/QuranInfo;", "audioRequest", "Lcom/salamquran/android/dao/audio/AudioRequest;", "initialPlaybackInfo", "Lcom/salamquran/android/dao/audio/AudioPlaybackInfo;", "(Lcom/salamquran/android/data/QuranInfo;Lcom/salamquran/android/dao/audio/AudioRequest;Lcom/salamquran/android/dao/audio/AudioPlaybackInfo;)V", "playbackInfo", "getCurrentAyah", "", "getCurrentSura", "getUrl", "", "isOutOfBounds", "", "sura", "ayah", "playAt", "skipAyahRepeat", "playNextAyah", "playPreviousAyah", "shouldRepeat", "repeatValue", "currentPlaybacks", "withUpdatedAudioRequest", "nextAyah", "Lcom/salamquran/android/data/SuraAyah;", "previousAyah", "app_madaniRelease"})
public final class AudioQueue {
    private com.salamquran.android.dao.audio.AudioPlaybackInfo playbackInfo;
    private final com.salamquran.android.data.QuranInfo quranInfo = null;
    private final com.salamquran.android.dao.audio.AudioRequest audioRequest = null;
    
    public final boolean playAt(int sura, int ayah, boolean skipAyahRepeat) {
        return false;
    }
    
    public final int getCurrentSura() {
        return 0;
    }
    
    public final int getCurrentAyah() {
        return 0;
    }
    
    public final boolean playNextAyah(boolean skipAyahRepeat) {
        return false;
    }
    
    public final boolean playPreviousAyah(boolean skipAyahRepeat) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.presenter.audio.service.AudioQueue withUpdatedAudioRequest(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.dao.audio.AudioRequest audioRequest) {
        return null;
    }
    
    private final boolean shouldRepeat(int repeatValue, int currentPlaybacks) {
        return false;
    }
    
    private final com.salamquran.android.data.SuraAyah nextAyah(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah $receiver) {
        return null;
    }
    
    private final com.salamquran.android.data.SuraAyah previousAyah(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah $receiver) {
        return null;
    }
    
    private final boolean isOutOfBounds(com.salamquran.android.dao.audio.AudioRequest audioRequest, int sura, int ayah) {
        return false;
    }
    
    public AudioQueue(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.QuranInfo quranInfo, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.dao.audio.AudioRequest audioRequest, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.dao.audio.AudioPlaybackInfo initialPlaybackInfo) {
        super();
    }
}