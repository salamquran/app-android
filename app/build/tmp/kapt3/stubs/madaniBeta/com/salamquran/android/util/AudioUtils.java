package com.salamquran.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\r\b\u0007\u0018\u0000 +2\u00020\u0001:\u0002+,B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0016J(\u0010\u0017\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\nJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0016J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0016J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160 2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010!\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0016J.\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\u0006\u0010\'\u001a\u00020\nJ\u0018\u0010(\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0013H\u0002J&\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\u0006\u0010\'\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/salamquran/android/util/AudioUtils;", "", "quranInfo", "Lcom/salamquran/android/data/QuranInfo;", "quranFileUtils", "Lcom/salamquran/android/util/QuranFileUtils;", "(Lcom/salamquran/android/data/QuranInfo;Lcom/salamquran/android/util/QuranFileUtils;)V", "totalPages", "", "doesRequireBasmallah", "", "minAyah", "Lcom/salamquran/android/data/SuraAyah;", "maxAyah", "getAudioIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "action", "", "getGaplessDatabaseUrl", "qari", "Lcom/salamquran/android/common/QariItem;", "getLastAyahToPlay", "startAyah", "currentPage", "mode", "isDualPages", "getLocalQariUrl", "item", "getQariDatabasePathIfGapless", "getQariList", "", "getQariUrl", "haveAllFiles", "baseUrl", "path", "start", "end", "isGapless", "haveAnyFiles", "shouldDownloadBasmallah", "baseDirectory", "Companion", "LookAheadAmount", "app_madaniBeta"})
@dagger.Reusable()
public final class AudioUtils {
    private final int totalPages = 0;
    private final com.salamquran.android.data.QuranInfo quranInfo = null;
    private final com.salamquran.android.util.QuranFileUtils quranFileUtils = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AUDIO_EXTENSION = ".mp3";
    private static final java.lang.String DB_EXTENSION = ".db";
    private static final java.lang.String ZIP_EXTENSION = ".zip";
    public static final com.salamquran.android.util.AudioUtils.Companion Companion = null;
    
    /**
     * * Get a list of QariItem representing the qaris to show
     *   *
     *   * This method takes into account qaris that exist both in gapped and gapless, and, in those
     *   * cases, hides the gapped version if it contains no files.
     *   *
     *   * @param context the current context
     *   * @return a list of QariItem representing the qaris to show.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.salamquran.android.common.QariItem> getQariList(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQariUrl(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QariItem item) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLocalQariUrl(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QariItem item) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getQariDatabasePathIfGapless(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QariItem item) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getGaplessDatabaseUrl(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QariItem qari) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.salamquran.android.data.SuraAyah getLastAyahToPlay(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah startAyah, int currentPage, int mode, boolean isDualPages) {
        return null;
    }
    
    public final boolean shouldDownloadBasmallah(@org.jetbrains.annotations.NotNull()
    java.lang.String baseDirectory, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah start, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah end, boolean isGapless) {
        return false;
    }
    
    @androidx.annotation.VisibleForTesting()
    public final boolean doesRequireBasmallah(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah minAyah, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah maxAyah) {
        return false;
    }
    
    private final boolean haveAnyFiles(android.content.Context context, java.lang.String path) {
        return false;
    }
    
    public final boolean haveAllFiles(@org.jetbrains.annotations.NotNull()
    java.lang.String baseUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String path, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah start, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah end, boolean isGapless) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Intent getAudioIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String action) {
        return null;
    }
    
    @javax.inject.Inject()
    public AudioUtils(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.QuranInfo quranInfo, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.QuranFileUtils quranFileUtils) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/salamquran/android/util/AudioUtils$LookAheadAmount;", "", "()V", "JUZ", "", "getJUZ", "()I", "MAX", "getMAX", "MIN", "getMIN", "PAGE", "getPAGE", "SURA", "getSURA", "app_madaniBeta"})
    public static final class LookAheadAmount {
        private static final int PAGE = 1;
        private static final int SURA = 2;
        private static final int JUZ = 3;
        private static final int MIN = 1;
        private static final int MAX = 3;
        public static final com.salamquran.android.util.AudioUtils.LookAheadAmount INSTANCE = null;
        
        public final int getPAGE() {
            return 0;
        }
        
        public final int getSURA() {
            return 0;
        }
        
        public final int getJUZ() {
            return 0;
        }
        
        public final int getMIN() {
            return 0;
        }
        
        public final int getMAX() {
            return 0;
        }
        
        private LookAheadAmount() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/salamquran/android/util/AudioUtils$Companion;", "", "()V", "AUDIO_EXTENSION", "", "DB_EXTENSION", "ZIP_EXTENSION", "haveSuraAyahForQari", "", "baseDir", "sura", "", "ayah", "app_madaniBeta"})
    public static final class Companion {
        
        public final boolean haveSuraAyahForQari(@org.jetbrains.annotations.NotNull()
        java.lang.String baseDir, int sura, int ayah) {
            return false;
        }
        
        private Companion() {
            super();
        }
    }
}