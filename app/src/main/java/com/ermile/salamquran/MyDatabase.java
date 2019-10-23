package com.ermile.salamquran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "quran_wbw.db";
    private static final int DATABASE_VERSION = 1;

    @SuppressLint("SdCardPath")
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, "/data/user/0/com.ermile.salamquran/files/", null, DATABASE_VERSION);
    }

    /*public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }*/
}