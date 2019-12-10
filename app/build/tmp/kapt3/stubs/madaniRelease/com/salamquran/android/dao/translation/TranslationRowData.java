package com.salamquran.android.dao.translation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&\u00a8\u0006\u0007"}, d2 = {"Lcom/salamquran/android/dao/translation/TranslationRowData;", "", "isSeparator", "", "name", "", "needsUpgrade", "app_madaniRelease"})
public abstract interface TranslationRowData {
    
    public abstract boolean isSeparator();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String name();
    
    public abstract boolean needsUpgrade();
}