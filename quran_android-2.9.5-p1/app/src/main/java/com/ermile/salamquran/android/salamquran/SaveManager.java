package com.ermile.salamquran.android.salamquran;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SaveManager extends ContextWrapper {

  SharedPreferences.Editor editor;
  SharedPreferences sharedPreferences;
  public static final String SH_PREF_NAME = "ShPerfManager_Payamres";


  @SuppressLint("CommitPrefEdits")
  private SaveManager(Context context) {
    super(context);
    sharedPreferences = getSharedPreferences(SH_PREF_NAME, MODE_PRIVATE);
    editor = sharedPreferences.edit();
  }
  public static SaveManager get(Context context) {
    return new SaveManager(context);
  }


  /** App Info */
  public static final String local = "local";
  public static final String apikey = "apikey";
  public static final String userCode = "userCode";
  public static final String zonId = "zonId";
  public static final String mobile = "mobile";

  public void save_local_url(String local_URL) {
    editor.putString(local, local_URL);
    editor.apply();
  }
  public void save_user_info(String Apikey, String UserCode, String ZonId, String Mobile) {
    editor.putString( apikey, Apikey);
    editor.putString( userCode, UserCode );
    editor.putString( zonId, ZonId);
    editor.putString( mobile, Mobile);
    editor.apply();
  }

  public Map<String, String> getstring_appINFO() {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put(local, sharedPreferences.getString(local, url.api ));

    hashMap.put(apikey, sharedPreferences.getString(apikey, "6f2d002a445db456d2bcea3dd02631af"));
    hashMap.put(userCode, sharedPreferences.getString(userCode, null));
    hashMap.put(zonId, sharedPreferences.getString(zonId, null));
    hashMap.put(mobile, sharedPreferences.getString(mobile, null));
    return hashMap;
  }



}


