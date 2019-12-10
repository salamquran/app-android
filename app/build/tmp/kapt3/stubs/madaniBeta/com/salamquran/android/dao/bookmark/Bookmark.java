package com.salamquran.android.dao.bookmark;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001BQ\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\fH\u00c6\u0003J`\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00c6\u0001\u00a2\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00d6\u0001J\u0006\u0010(\u001a\u00020%J\t\u0010)\u001a\u00020\fH\u00d6\u0001J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010+\u001a\u00020\u00002\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030\nR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0017\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014\u00a8\u0006-"}, d2 = {"Lcom/salamquran/android/dao/bookmark/Bookmark;", "", "id", "", "sura", "", "ayah", "page", "timestamp", "tags", "", "ayahText", "", "(JLjava/lang/Integer;Ljava/lang/Integer;IJLjava/util/List;Ljava/lang/String;)V", "getAyah", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAyahText", "()Ljava/lang/String;", "getId", "()J", "getPage", "()I", "getSura", "getTags", "()Ljava/util/List;", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JLjava/lang/Integer;Ljava/lang/Integer;IJLjava/util/List;Ljava/lang/String;)Lcom/salamquran/android/dao/bookmark/Bookmark;", "equals", "", "other", "hashCode", "isPageBookmark", "toString", "withAyahText", "withTags", "tagIds", "app_madaniBeta"})
public final class Bookmark {
    private final long id = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer sura = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer ayah = null;
    private final int page = 0;
    private final long timestamp = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Long> tags = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ayahText = null;
    
    public final boolean isPageBookmark() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.dao.bookmark.Bookmark withTags(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> tagIds) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.dao.bookmark.Bookmark withAyahText(@org.jetbrains.annotations.NotNull()
    java.lang.String ayahText) {
        return null;
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getSura() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getAyah() {
        return null;
    }
    
    public final int getPage() {
        return 0;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Long> getTags() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAyahText() {
        return null;
    }
    
    public Bookmark(long id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer sura, @org.jetbrains.annotations.Nullable()
    java.lang.Integer ayah, int page, long timestamp, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> tags, @org.jetbrains.annotations.Nullable()
    java.lang.String ayahText) {
        super();
    }
    
    public Bookmark(long id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer sura, @org.jetbrains.annotations.Nullable()
    java.lang.Integer ayah, int page, long timestamp, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> tags) {
        super();
    }
    
    public Bookmark(long id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer sura, @org.jetbrains.annotations.Nullable()
    java.lang.Integer ayah, int page, long timestamp) {
        super();
    }
    
    public Bookmark(long id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer sura, @org.jetbrains.annotations.Nullable()
    java.lang.Integer ayah, int page) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Long> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.dao.bookmark.Bookmark copy(long id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer sura, @org.jetbrains.annotations.Nullable()
    java.lang.Integer ayah, int page, long timestamp, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> tags, @org.jetbrains.annotations.Nullable()
    java.lang.String ayahText) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}