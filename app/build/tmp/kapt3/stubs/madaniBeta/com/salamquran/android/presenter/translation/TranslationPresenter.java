package com.salamquran.android.presenter.translation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"BG\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J1\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00102\u0006\u0010\u001f\u001a\u00020\u0011\u00a2\u0006\u0002\u0010 J\u0006\u0010!\u001a\u00020\u0019R\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/salamquran/android/presenter/translation/TranslationPresenter;", "Lcom/salamquran/android/presenter/translation/BaseTranslationPresenter;", "Lcom/salamquran/android/presenter/translation/TranslationPresenter$TranslationScreen;", "translationModel", "Lcom/salamquran/android/model/translation/TranslationModel;", "quranSettings", "Lcom/salamquran/android/util/QuranSettings;", "translationsAdapter", "Lcom/salamquran/android/database/TranslationsDBAdapter;", "translationUtil", "Lcom/salamquran/android/util/TranslationUtil;", "shareUtil", "Lcom/salamquran/android/util/ShareUtil;", "quranInfo", "Lcom/salamquran/android/data/QuranInfo;", "pages", "", "", "(Lcom/salamquran/android/model/translation/TranslationModel;Lcom/salamquran/android/util/QuranSettings;Lcom/salamquran/android/database/TranslationsDBAdapter;Lcom/salamquran/android/util/TranslationUtil;Lcom/salamquran/android/util/ShareUtil;Lcom/salamquran/android/data/QuranInfo;[Ljava/lang/Integer;)V", "[Ljava/lang/Integer;", "getPage", "result", "", "Lcom/salamquran/android/common/QuranAyahInfo;", "onTranslationAction", "", "activity", "Lcom/salamquran/android/ui/PagerActivity;", "ayah", "translationNames", "Lcom/salamquran/android/common/LocalTranslation;", "actionId", "(Lcom/salamquran/android/ui/PagerActivity;Lcom/salamquran/android/common/QuranAyahInfo;[Lcom/salamquran/android/common/LocalTranslation;I)V", "refresh", "TranslationScreen", "app_madaniBeta"})
@com.salamquran.android.di.QuranPageScope()
public final class TranslationPresenter extends com.salamquran.android.presenter.translation.BaseTranslationPresenter<com.salamquran.android.presenter.translation.TranslationPresenter.TranslationScreen> {
    private final com.salamquran.android.util.QuranSettings quranSettings = null;
    private final com.salamquran.android.util.ShareUtil shareUtil = null;
    private final com.salamquran.android.data.QuranInfo quranInfo = null;
    private final java.lang.Integer[] pages = null;
    
    public final void refresh() {
    }
    
    public final void onTranslationAction(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.ui.PagerActivity activity, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QuranAyahInfo ayah, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.LocalTranslation[] translationNames, int actionId) {
    }
    
    private final int getPage(java.util.List<? extends com.salamquran.android.common.QuranAyahInfo> result) {
        return 0;
    }
    
    @javax.inject.Inject()
    public TranslationPresenter(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.model.translation.TranslationModel translationModel, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.QuranSettings quranSettings, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.database.TranslationsDBAdapter translationsAdapter, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.TranslationUtil translationUtil, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.ShareUtil shareUtil, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.QuranInfo quranInfo, @org.jetbrains.annotations.NotNull()
    java.lang.Integer[] pages) {
        super(null, null, null, null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0011\u0010\t\u001a\r\u0012\t\u0012\u00070\u000b\u00a2\u0006\u0002\b\f0\nH&\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u0003H&\u00a8\u0006\u000f"}, d2 = {"Lcom/salamquran/android/presenter/translation/TranslationPresenter$TranslationScreen;", "", "setVerses", "", "page", "", "translations", "", "Lcom/salamquran/android/common/LocalTranslation;", "verses", "", "Lcom/salamquran/android/common/QuranAyahInfo;", "Lkotlin/jvm/JvmSuppressWildcards;", "(I[Lcom/salamquran/android/common/LocalTranslation;Ljava/util/List;)V", "updateScrollPosition", "app_madaniBeta"})
    public static abstract interface TranslationScreen {
        
        public abstract void setVerses(int page, @org.jetbrains.annotations.NotNull()
        com.salamquran.android.common.LocalTranslation[] translations, @org.jetbrains.annotations.NotNull()
        java.util.List<com.salamquran.android.common.QuranAyahInfo> verses);
        
        public abstract void updateScrollPosition();
    }
}