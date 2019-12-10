package com.salamquran.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/salamquran/android/util/TranslationUtil;", "", "color", "", "quranInfo", "Lcom/salamquran/android/data/QuranInfo;", "(ILcom/salamquran/android/data/QuranInfo;)V", "parseTranslationText", "Lcom/salamquran/android/common/TranslationMetadata;", "quranText", "Lcom/salamquran/android/common/QuranText;", "Companion", "app_madaniBeta"})
@dagger.Reusable()
public class TranslationUtil {
    private final int color = 0;
    private final com.salamquran.android.data.QuranInfo quranInfo = null;
    private static final kotlin.text.Regex ayahRegex = null;
    private static final kotlin.text.Regex footerRegex = null;
    public static final int MINIMUM_PROCESSING_VERSION = 5;
    public static final com.salamquran.android.util.TranslationUtil.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public com.salamquran.android.common.TranslationMetadata parseTranslationText(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QuranText quranText) {
        return null;
    }
    
    public TranslationUtil(@androidx.annotation.ColorInt()
    int color, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.QuranInfo quranInfo) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.Integer getHyperlinkAyahId(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QuranText quranText) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0017\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\nH\u0007\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/salamquran/android/util/TranslationUtil$Companion;", "", "()V", "MINIMUM_PROCESSING_VERSION", "", "ayahRegex", "Lkotlin/text/Regex;", "footerRegex", "getHyperlinkAyahId", "quranText", "Lcom/salamquran/android/common/QuranText;", "(Lcom/salamquran/android/common/QuranText;)Ljava/lang/Integer;", "app_madaniBeta"})
    public static final class Companion {
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getHyperlinkAyahId(@org.jetbrains.annotations.NotNull()
        com.salamquran.android.common.QuranText quranText) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}