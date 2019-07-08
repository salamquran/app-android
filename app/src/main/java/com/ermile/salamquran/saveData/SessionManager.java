package com.ermile.salamquran.saveData;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SessionManager extends ContextWrapper {

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    public static final String SH_PREF_NAME = "pref_name";




    @SuppressLint("CommitPrefEdits")
    private SessionManager(Context base) {
        super(base);
        sharedPreferences = getSharedPreferences(SH_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static SessionManager get(Context base) {
        return new SessionManager(base);
    }


    /*Update Version*/
    public static final String pref_UpdateTitle = "UpdateTitle";
    public static final String pref_UpdateDesc = "UpdateDesc";
    public static final String pref_UpdateURL = "UpdateURL";
    public void saveUpdate(String Title,String Desc, String URL) {
        editor.putString(pref_UpdateTitle, Title);
        editor.putString(pref_UpdateDesc, Desc);
        editor.putString(pref_UpdateURL, URL);
        editor.apply();
    }
    public Map<String, String> getUpdate() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(pref_UpdateTitle, sharedPreferences
                .getString(pref_UpdateTitle, "New version is released"));
        hashMap.put(pref_UpdateDesc, sharedPreferences
                .getString(pref_UpdateDesc, "To download new version of this app click blow link"));
        hashMap.put(pref_UpdateURL, sharedPreferences
                .getString(pref_UpdateURL, Value.site));
        return hashMap;
    }

    /*Deprecate Version*/
    public static final String pref_deprecatTitle = "deprecatTitle";
    public static final String pref_deprecatDesc = "deprecatDesc";
    public static final String pref_deprecatBtn = "deprecatBtn";
    public static final String pref_deprecatURL = "deprecatURL";
    public void saveDeprecat(String Title, String desc,String Btn, String URL) {
        editor.putString(pref_deprecatTitle, Title);
        editor.putString(pref_deprecatDesc, desc);
        editor.putString(pref_deprecatBtn, Btn);
        editor.putString(pref_deprecatURL, URL);
        editor.apply();
    }
    public Map<String, String> getDeprecat() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(pref_deprecatTitle, sharedPreferences
                .getString(pref_deprecatTitle, "This version is deprecated"));
        hashMap.put(pref_deprecatDesc, sharedPreferences
                .getString(pref_deprecatDesc, "To download new version of this app click blow link"));
        hashMap.put(pref_deprecatBtn, sharedPreferences.
                getString(pref_deprecatBtn, "Download"));
        hashMap.put(pref_deprecatURL, sharedPreferences
                .getString(pref_deprecatURL,Value.site ));
        return hashMap;
    }


}
