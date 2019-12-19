package com.ermile.salamquran.android.salamquran.Utility;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.ermile.salamquran.android.R;

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
  public static final String splash = "splash";
  public static final String appLanguage = "appLanguage";

  public static final String apikey = "apikey";
  public static final String userCode = "userCode";
  public static final String zonId = "zonId";
  public static final String mobile = "mobile";

  public static final String jsonIntro = "jsonIntro";
  public static final String jsonLanguageList = "jsonLanguageList";
  public static final String jsonAyaDay = "jsonAyaDay";
  public static final String jsonPageDay = "jsonPageDay";

  public void save_local_url(String local_URL) {
    editor.putString(local, local_URL);
    editor.apply();
  }
  public void save_splash(int id) {
    editor.putInt(splash, id);
    editor.apply();
  }
  public void save_app_language(String languageLocal) {
    editor.putString(appLanguage, languageLocal);
    editor.apply();
  }
  public void save_user_info(String Apikey, String UserCode, String ZonId, String Mobile) {
    editor.putString( apikey, Apikey);
    editor.putString( userCode, UserCode );
    editor.putString( zonId, ZonId);
    editor.putString( mobile, Mobile);
    editor.apply();
  }

  public void save_json_intro(String json) {
    editor.putString(jsonIntro, json);
    editor.apply();
  }
  public void save_json_languageList(String json) {
    editor.putString(jsonLanguageList, json);
    editor.apply();
  }
  public void save_json_ayaDay(String json) {
    editor.putString(jsonAyaDay, json);
    editor.apply();
  }
  public void save_json_pageDay(String json) {
    editor.putString(jsonPageDay, json);
    editor.apply();
  }

  public Map<String, String> getstring_appINFO() {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put(local, sharedPreferences.getString(local, Url.api ));

    hashMap.put(appLanguage, sharedPreferences.getString(appLanguage, "en" ));

    hashMap.put(apikey, sharedPreferences.getString(apikey, null));
    hashMap.put(userCode, sharedPreferences.getString(userCode, null));
    hashMap.put(zonId, sharedPreferences.getString(zonId, null));
    hashMap.put(mobile, sharedPreferences.getString(mobile, null));

    hashMap.put(jsonIntro, sharedPreferences.
        getString(jsonIntro, Json.defaultValue.intro_en));
    hashMap.put(jsonLanguageList, sharedPreferences
        .getString(jsonLanguageList, Json.defaultValue.appLanguage));
    hashMap.put(jsonAyaDay, sharedPreferences.getString(jsonAyaDay, Json.defaultValue.ayaDay));
    hashMap.put(jsonPageDay, sharedPreferences.getString(jsonPageDay, Json.defaultValue.pageDay));
    return hashMap;
  }

  public Map<String, Integer> getIntValue() {
    HashMap<String, Integer> hashMap = new HashMap<>();
    hashMap.put(splash, sharedPreferences.getInt(splash, 0 ));
    return hashMap;
  }


}


