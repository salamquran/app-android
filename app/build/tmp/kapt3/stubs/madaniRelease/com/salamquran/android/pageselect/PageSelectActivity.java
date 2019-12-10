package com.salamquran.android.pageselect;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0014J\b\u0010\u001c\u001a\u00020\u0014H\u0014J\u0014\u0010\u001d\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/salamquran/android/pageselect/PageSelectActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/salamquran/android/pageselect/PageSelectAdapter;", "presenter", "Lcom/salamquran/android/pageselect/PageSelectPresenter;", "getPresenter", "()Lcom/salamquran/android/pageselect/PageSelectPresenter;", "setPresenter", "(Lcom/salamquran/android/pageselect/PageSelectPresenter;)V", "quranSettings", "Lcom/salamquran/android/util/QuranSettings;", "getQuranSettings", "()Lcom/salamquran/android/util/QuranSettings;", "setQuranSettings", "(Lcom/salamquran/android/util/QuranSettings;)V", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPageTypeSelected", "type", "", "onPause", "onResume", "onUpdatedData", "data", "", "Lcom/salamquran/android/pageselect/PageTypeItem;", "app_madaniRelease"})
public final class PageSelectActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.salamquran.android.pageselect.PageSelectPresenter presenter;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.salamquran.android.util.QuranSettings quranSettings;
    private com.salamquran.android.pageselect.PageSelectAdapter adapter;
    private androidx.viewpager.widget.ViewPager viewPager;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.pageselect.PageSelectPresenter getPresenter() {
        return null;
    }
    
    public final void setPresenter(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.pageselect.PageSelectPresenter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.util.QuranSettings getQuranSettings() {
        return null;
    }
    
    public final void setQuranSettings(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.util.QuranSettings p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public final void onUpdatedData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.salamquran.android.pageselect.PageTypeItem> data) {
    }
    
    private final void onPageTypeSelected(java.lang.String type) {
    }
    
    public PageSelectActivity() {
        super();
    }
}