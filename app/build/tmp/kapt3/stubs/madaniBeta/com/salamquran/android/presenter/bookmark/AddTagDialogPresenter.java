package com.salamquran.android.presenter.bookmark;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\tJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/salamquran/android/presenter/bookmark/AddTagDialogPresenter;", "Lcom/salamquran/android/presenter/Presenter;", "Lcom/salamquran/android/ui/fragment/AddTagDialog;", "bookmarkModel", "Lcom/salamquran/android/model/bookmark/BookmarkModel;", "(Lcom/salamquran/android/model/bookmark/BookmarkModel;)V", "dialog", "tags", "", "Lcom/salamquran/android/dao/Tag;", "addTag", "", "tagName", "", "bind", "unbind", "updateTag", "tag", "validate", "", "tagId", "", "app_madaniBeta"})
public final class AddTagDialogPresenter implements com.salamquran.android.presenter.Presenter<com.salamquran.android.ui.fragment.AddTagDialog> {
    private com.salamquran.android.ui.fragment.AddTagDialog dialog;
    private java.util.List<com.salamquran.android.dao.Tag> tags;
    private final com.salamquran.android.model.bookmark.BookmarkModel bookmarkModel = null;
    
    public final boolean validate(@org.jetbrains.annotations.NotNull()
    java.lang.String tagName, long tagId) {
        return false;
    }
    
    public final void addTag(@org.jetbrains.annotations.NotNull()
    java.lang.String tagName) {
    }
    
    public final void updateTag(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.dao.Tag tag) {
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.ui.fragment.AddTagDialog dialog) {
    }
    
    @java.lang.Override()
    public void unbind(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.ui.fragment.AddTagDialog dialog) {
    }
    
    @javax.inject.Inject()
    public AddTagDialogPresenter(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.model.bookmark.BookmarkModel bookmarkModel) {
        super();
    }
}