package com.salamquran.android.pageselect;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0016\u001a\u00020\tJ \u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0005H\u0016J\u0018\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001cH\u0016J\u001e\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J\u001c\u0010)\u001a\u00020\t2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00110+2\u0006\u0010,\u001a\u00020-J\u0018\u0010.\u001a\u00020\t2\u0006\u0010!\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u0011H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u00060"}, d2 = {"Lcom/salamquran/android/pageselect/PageSelectAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "inflater", "Landroid/view/LayoutInflater;", "width", "", "selectionHandler", "Lkotlin/Function1;", "", "", "(Landroid/view/LayoutInflater;ILkotlin/jvm/functions/Function1;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getInflater", "()Landroid/view/LayoutInflater;", "items", "", "Lcom/salamquran/android/pageselect/PageTypeItem;", "listener", "Landroid/view/View$OnClickListener;", "getWidth", "()I", "cleanUp", "destroyItem", "container", "Landroid/view/ViewGroup;", "position", "o", "", "getCount", "instantiateItem", "isViewFromObject", "", "view", "Landroid/view/View;", "obj", "readImage", "path", "imageRef", "Ljava/lang/ref/WeakReference;", "Landroid/widget/ImageView;", "replaceItems", "updates", "", "pager", "Landroidx/viewpager/widget/ViewPager;", "updateView", "data", "app_madaniBeta"})
public final class PageSelectAdapter extends androidx.viewpager.widget.PagerAdapter {
    private final java.util.List<com.salamquran.android.pageselect.PageTypeItem> items = null;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final android.view.View.OnClickListener listener = null;
    @org.jetbrains.annotations.NotNull()
    private final android.view.LayoutInflater inflater = null;
    private final int width = 0;
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> selectionHandler = null;
    
    public final void replaceItems(@org.jetbrains.annotations.NotNull()
    java.util.List<com.salamquran.android.pageselect.PageTypeItem> updates, @org.jetbrains.annotations.NotNull()
    androidx.viewpager.widget.ViewPager pager) {
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean isViewFromObject(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    java.lang.Object obj) {
        return false;
    }
    
    private final void updateView(android.view.View view, com.salamquran.android.pageselect.PageTypeItem data) {
    }
    
    private final void readImage(java.lang.String path, java.lang.ref.WeakReference<android.widget.ImageView> imageRef) {
    }
    
    public final void cleanUp() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.Object instantiateItem(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup container, int position) {
        return null;
    }
    
    @java.lang.Override()
    public void destroyItem(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup container, int position, @org.jetbrains.annotations.NotNull()
    java.lang.Object o) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.LayoutInflater getInflater() {
        return null;
    }
    
    public final int getWidth() {
        return 0;
    }
    
    public PageSelectAdapter(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, int width, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> selectionHandler) {
        super();
    }
}