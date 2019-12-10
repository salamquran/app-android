package com.salamquran.android.presenter.translation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001;B\'\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0015\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u001cJI\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\"2\u0012\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\'0\"0\"2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00170*\u00a2\u0006\u0002\u0010+J\"\u0010,\u001a\b\u0012\u0004\u0012\u00020\'0\"2\u0006\u0010$\u001a\u00020%2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\'0\"J\u001a\u0010.\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017000/H\u0002J\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\u00160\"2\u0006\u00102\u001a\u000203J3\u00101\u001a\b\u0012\u0004\u0012\u00020\u00170*2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00160\"2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u001700\u00a2\u0006\u0002\u00105J*\u00106\u001a\b\u0012\u0004\u0012\u0002070/2\u0006\u00108\u001a\u0002092\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00160\"2\u0006\u0010$\u001a\u00020%J\u0015\u0010:\u001a\u00020\u001f2\u0006\u0010 \u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u001cR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0004\u0018\u00018\u0000X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/salamquran/android/presenter/translation/BaseTranslationPresenter;", "T", "Lcom/salamquran/android/presenter/Presenter;", "translationModel", "Lcom/salamquran/android/model/translation/TranslationModel;", "translationsAdapter", "Lcom/salamquran/android/database/TranslationsDBAdapter;", "translationUtil", "Lcom/salamquran/android/util/TranslationUtil;", "quranInfo", "Lcom/salamquran/android/data/QuranInfo;", "(Lcom/salamquran/android/model/translation/TranslationModel;Lcom/salamquran/android/database/TranslationsDBAdapter;Lcom/salamquran/android/util/TranslationUtil;Lcom/salamquran/android/data/QuranInfo;)V", "disposable", "Lio/reactivex/disposables/Disposable;", "getDisposable", "()Lio/reactivex/disposables/Disposable;", "setDisposable", "(Lio/reactivex/disposables/Disposable;)V", "lastCacheTime", "", "translationMap", "", "", "Lcom/salamquran/android/common/LocalTranslation;", "translationScreen", "getTranslationScreen", "()Ljava/lang/Object;", "setTranslationScreen", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "bind", "", "what", "combineAyahData", "", "Lcom/salamquran/android/common/QuranAyahInfo;", "verseRange", "Lcom/salamquran/android/data/VerseRange;", "arabic", "Lcom/salamquran/android/common/QuranText;", "texts", "translationInfo", "", "(Lcom/salamquran/android/data/VerseRange;Ljava/util/List;Ljava/util/List;[Lcom/salamquran/android/common/LocalTranslation;)Ljava/util/List;", "ensureProperTranslations", "inputTexts", "getTranslationMapSingle", "Lio/reactivex/Single;", "", "getTranslations", "quranSettings", "Lcom/salamquran/android/util/QuranSettings;", "translations", "(Ljava/util/List;Ljava/util/Map;)[Lcom/salamquran/android/common/LocalTranslation;", "getVerses", "Lcom/salamquran/android/presenter/translation/BaseTranslationPresenter$ResultHolder;", "getArabic", "", "unbind", "ResultHolder", "app_madaniBeta"})
public class BaseTranslationPresenter<T extends java.lang.Object> implements com.salamquran.android.presenter.Presenter<T> {
    private long lastCacheTime;
    private final java.util.Map<java.lang.String, com.salamquran.android.common.LocalTranslation> translationMap = null;
    @org.jetbrains.annotations.Nullable()
    private T translationScreen;
    @org.jetbrains.annotations.Nullable()
    private io.reactivex.disposables.Disposable disposable;
    private final com.salamquran.android.model.translation.TranslationModel translationModel = null;
    private final com.salamquran.android.database.TranslationsDBAdapter translationsAdapter = null;
    private final com.salamquran.android.util.TranslationUtil translationUtil = null;
    private final com.salamquran.android.data.QuranInfo quranInfo = null;
    
    @org.jetbrains.annotations.Nullable()
    public final T getTranslationScreen() {
        return null;
    }
    
    public final void setTranslationScreen(@org.jetbrains.annotations.Nullable()
    T p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final io.reactivex.disposables.Disposable getDisposable() {
        return null;
    }
    
    public final void setDisposable(@org.jetbrains.annotations.Nullable()
    io.reactivex.disposables.Disposable p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.salamquran.android.presenter.translation.BaseTranslationPresenter.ResultHolder> getVerses(boolean getArabic, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> translations, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.VerseRange verseRange) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTranslations(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.QuranSettings quranSettings) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.common.LocalTranslation[] getTranslations(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> translations, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.salamquran.android.common.LocalTranslation> translationMap) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.salamquran.android.common.QuranAyahInfo> combineAyahData(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.VerseRange verseRange, @org.jetbrains.annotations.NotNull()
    java.util.List<com.salamquran.android.common.QuranText> arabic, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.List<com.salamquran.android.common.QuranText>> texts, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.LocalTranslation[] translationInfo) {
        return null;
    }
    
    /**
     * * Ensures that the list of translations is valid
     *   * In this case, valid means that the number of verses that we have translations for is either
     *   * the same as the verse range, or that it's 0 (i.e. due to an error querying the database). If
     *   * the list has a non-zero length that is less than what the verseRange says, it adds empty
     *   * entries for those.
     *   *
     *   * @param verseRange the range of verses we're trying to get
     *   * @param inputTexts the data we got back from the database
     *   * @return a list of QuranText with a length of either 0 or the verse range
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.salamquran.android.common.QuranText> ensureProperTranslations(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.VerseRange verseRange, @org.jetbrains.annotations.NotNull()
    java.util.List<com.salamquran.android.common.QuranText> inputTexts) {
        return null;
    }
    
    private final io.reactivex.Single<java.util.Map<java.lang.String, com.salamquran.android.common.LocalTranslation>> getTranslationMapSingle() {
        return null;
    }
    
    @java.lang.Override()
    public void bind(T what) {
    }
    
    @java.lang.Override()
    public void unbind(T what) {
    }
    
    public BaseTranslationPresenter(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.model.translation.TranslationModel translationModel, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.database.TranslationsDBAdapter translationsAdapter, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.TranslationUtil translationUtil, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.QuranInfo quranInfo) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000e"}, d2 = {"Lcom/salamquran/android/presenter/translation/BaseTranslationPresenter$ResultHolder;", "", "translations", "", "Lcom/salamquran/android/common/LocalTranslation;", "ayahInformation", "", "Lcom/salamquran/android/common/QuranAyahInfo;", "([Lcom/salamquran/android/common/LocalTranslation;Ljava/util/List;)V", "getAyahInformation", "()Ljava/util/List;", "getTranslations", "()[Lcom/salamquran/android/common/LocalTranslation;", "[Lcom/salamquran/android/common/LocalTranslation;", "app_madaniBeta"})
    public static final class ResultHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.salamquran.android.common.LocalTranslation[] translations = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.salamquran.android.common.QuranAyahInfo> ayahInformation = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.salamquran.android.common.LocalTranslation[] getTranslations() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.salamquran.android.common.QuranAyahInfo> getAyahInformation() {
            return null;
        }
        
        public ResultHolder(@org.jetbrains.annotations.NotNull()
        com.salamquran.android.common.LocalTranslation[] translations, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends com.salamquran.android.common.QuranAyahInfo> ayahInformation) {
            super();
        }
    }
}