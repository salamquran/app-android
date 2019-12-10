package com.salamquran.android.ui.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0006H\u0016R4\u0010\u0004\u001a\u0018\u0012\t\u0012\u00070\u0006\u00a2\u0006\u0002\b\u0007\u0012\t\u0012\u00070\b\u00a2\u0006\u0002\b\u00070\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u001c"}, d2 = {"Lcom/salamquran/android/ui/fragment/QuranSettingsFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "pageTypes", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "Lcom/ermile/data/source/PageProvider;", "getPageTypes", "()Ljava/util/Map;", "setPageTypes", "(Ljava/util/Map;)V", "onCreatePreferences", "", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "onPause", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onResume", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "app_madaniBeta"})
public final class QuranSettingsFragment extends androidx.preference.PreferenceFragmentCompat implements android.content.SharedPreferences.OnSharedPreferenceChangeListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public java.util.Map<java.lang.String, com.ermile.data.source.PageProvider> pageTypes;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.ermile.data.source.PageProvider> getPageTypes() {
        return null;
    }
    
    public final void setPageTypes(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.ermile.data.source.PageProvider> p0) {
    }
    
    @java.lang.Override()
    public void onCreatePreferences(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.Nullable()
    java.lang.String rootKey) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onSharedPreferenceChanged(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences, @org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    @java.lang.Override()
    public boolean onPreferenceTreeClick(@org.jetbrains.annotations.NotNull()
    androidx.preference.Preference preference) {
        return false;
    }
    
    public QuranSettingsFragment() {
        super();
    }
}