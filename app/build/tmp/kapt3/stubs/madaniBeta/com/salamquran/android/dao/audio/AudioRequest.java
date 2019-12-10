package com.salamquran.android.dao.audio;

import java.lang.System;

@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00c6\u0003J\t\u0010 \u001a\u00020\bH\u00c6\u0003J\t\u0010!\u001a\u00020\bH\u00c6\u0003J\t\u0010\"\u001a\u00020\u000bH\u00c6\u0003J\t\u0010#\u001a\u00020\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\u000eH\u00c6\u0003JY\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\t\u0010&\u001a\u00020\bH\u00d6\u0001J\u0013\u0010\'\u001a\u00020\u000b2\b\u0010(\u001a\u0004\u0018\u00010)H\u00d6\u0003J\t\u0010*\u001a\u00020\bH\u00d6\u0001J\u0006\u0010+\u001a\u00020\u000bJ\u0006\u0010,\u001a\u00020\u000bJ\t\u0010-\u001a\u00020.H\u00d6\u0001J\u0019\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013\u00a8\u00064"}, d2 = {"Lcom/salamquran/android/dao/audio/AudioRequest;", "Landroid/os/Parcelable;", "start", "Lcom/salamquran/android/data/SuraAyah;", "end", "qari", "Lcom/salamquran/android/common/QariItem;", "repeatInfo", "", "rangeRepeatInfo", "enforceBounds", "", "shouldStream", "audioPathInfo", "Lcom/salamquran/android/dao/audio/AudioPathInfo;", "(Lcom/salamquran/android/data/SuraAyah;Lcom/salamquran/android/data/SuraAyah;Lcom/salamquran/android/common/QariItem;IIZZLcom/salamquran/android/dao/audio/AudioPathInfo;)V", "getAudioPathInfo", "()Lcom/salamquran/android/dao/audio/AudioPathInfo;", "getEnd", "()Lcom/salamquran/android/data/SuraAyah;", "getEnforceBounds", "()Z", "getQari", "()Lcom/salamquran/android/common/QariItem;", "getRangeRepeatInfo", "()I", "getRepeatInfo", "getShouldStream", "getStart", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "equals", "other", "", "hashCode", "isGapless", "needsIsti3athaAudio", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_madaniBeta"})
public final class AudioRequest implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final com.salamquran.android.data.SuraAyah start = null;
    @org.jetbrains.annotations.NotNull()
    private final com.salamquran.android.data.SuraAyah end = null;
    @org.jetbrains.annotations.NotNull()
    private final com.salamquran.android.common.QariItem qari = null;
    private final int repeatInfo = 0;
    private final int rangeRepeatInfo = 0;
    private final boolean enforceBounds = false;
    private final boolean shouldStream = false;
    @org.jetbrains.annotations.NotNull()
    private final com.salamquran.android.dao.audio.AudioPathInfo audioPathInfo = null;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    public final boolean isGapless() {
        return false;
    }
    
    public final boolean needsIsti3athaAudio() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.data.SuraAyah getStart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.data.SuraAyah getEnd() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.common.QariItem getQari() {
        return null;
    }
    
    public final int getRepeatInfo() {
        return 0;
    }
    
    public final int getRangeRepeatInfo() {
        return 0;
    }
    
    public final boolean getEnforceBounds() {
        return false;
    }
    
    public final boolean getShouldStream() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.dao.audio.AudioPathInfo getAudioPathInfo() {
        return null;
    }
    
    public AudioRequest(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah start, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah end, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QariItem qari, int repeatInfo, int rangeRepeatInfo, boolean enforceBounds, boolean shouldStream, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.dao.audio.AudioPathInfo audioPathInfo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.data.SuraAyah component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.data.SuraAyah component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.common.QariItem component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.dao.audio.AudioPathInfo component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.salamquran.android.dao.audio.AudioRequest copy(@org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah start, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.data.SuraAyah end, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.common.QariItem qari, int repeatInfo, int rangeRepeatInfo, boolean enforceBounds, boolean shouldStream, @org.jetbrains.annotations.NotNull()
    com.salamquran.android.dao.audio.AudioPathInfo audioPathInfo) {
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
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
}