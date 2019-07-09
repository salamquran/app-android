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


    /** App Language*/
    public static final String pref_appLanguage = "appLanguage";
    public static final String pref_app = "app";
    public void saveAppLanguage(String language,String App ) {
        editor.putString(pref_appLanguage, language);
        editor.putString(pref_app, App);
        editor.apply();
    }
    public Map<String, String> getAppLanguage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(pref_appLanguage, sharedPreferences
                .getString(pref_appLanguage, null));
        hashMap.put(pref_app, sharedPreferences
                .getString(pref_app, null));
        return hashMap;
    }

    /** Add User*/
    public static final String pref_usercode = "usercode";
    public static final String pref_zoneid = "zoneid";
    public static final String pref_apikey = "apikey";
    public void saveUser(String usercode ,String zoneid , String apikey) {
        editor.putString(pref_usercode, usercode);
        editor.putString(pref_zoneid, zoneid);
        editor.putString(pref_apikey, apikey);
        editor.apply();
    }
    public Map<String, String> getUser() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(pref_usercode, sharedPreferences.getString(pref_usercode, null ));
        hashMap.put(pref_zoneid, sharedPreferences.getString(pref_zoneid, null ));
        hashMap.put(pref_apikey, sharedPreferences.getString(pref_apikey, null ));
        return hashMap;
    }


    /** Update Version*/
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



}
