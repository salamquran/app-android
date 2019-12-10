package com.salamquran.android.ui.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0006\u0010\u0012\u001a\u00020\fJ\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0006\u0010\u0015\u001a\u00020\fJ\b\u0010\u0016\u001a\u00020\fH\u0016J\b\u0010\u0017\u001a\u00020\fH\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/salamquran/android/ui/fragment/AddTagDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "addTagDialogPresenter", "Lcom/salamquran/android/presenter/bookmark/AddTagDialogPresenter;", "getAddTagDialogPresenter$app_madaniBeta", "()Lcom/salamquran/android/presenter/bookmark/AddTagDialogPresenter;", "setAddTagDialogPresenter$app_madaniBeta", "(Lcom/salamquran/android/presenter/bookmark/AddTagDialogPresenter;)V", "textInputEditText", "Lcom/google/android/material/textfield/TextInputEditText;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onBlankTagName", "onCreateDialog", "Landroid/app/Dialog;", "onDuplicateTagName", "onResume", "onStart", "onStop", "Companion", "app_madaniBeta"})
public final class AddTagDialog extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.salamquran.android.presenter.bookmark.AddTagDialogPresenter addTagDialogPresenter;
    private com.google.android.material.textfield.TextInputEditText textInputEditText;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "AddTagDialog";
    private static final java.lang.String EXTRA_ID = "id";
    private static final java.lang.String EXTRA_NAME = "name";
    public static final com.salamquran.android.ui.fragment.AddTagDialog.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.presenter.bookmark.AddTagDialogPresenter getAddTagDialogPresenter$app_madaniBeta() {
        return null;
    }
    
    public final void setAddTagDialogPresenter$app_madaniBeta(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.presenter.bookmark.AddTagDialogPresenter p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    public final void onBlankTagName() {
    }
    
    public final void onDuplicateTagName() {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public AddTagDialog() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/salamquran/android/ui/fragment/AddTagDialog$Companion;", "", "()V", "EXTRA_ID", "", "EXTRA_NAME", "TAG", "newInstance", "Lcom/salamquran/android/ui/fragment/AddTagDialog;", "id", "", "name", "app_madaniBeta"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.salamquran.android.ui.fragment.AddTagDialog newInstance(long id, @org.jetbrains.annotations.NotNull()
        java.lang.String name) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}