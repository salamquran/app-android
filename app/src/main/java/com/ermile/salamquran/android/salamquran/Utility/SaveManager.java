package com.ermile.salamquran.android.salamquran.Utility;


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
  public static final String unZipFileReq = "unZipFileReq";

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

  public static final String url_site = "url_site";
  public static final String url_kingdom = "url_kingdom";
  public static final String url_domain = "url_domain";
  public static final String url_root = "url_root";
  public static final String url_update = "url_update";

  public static final String updateVersion = "updateVersion";
  public static final String deprecatedVersion = "deprecatedVersion";

  public void save_status_unzip_file_rq(Boolean state) {
    editor.putBoolean(unZipFileReq, state);
    editor.apply();
  }

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

  public void save_url(String site, String kingdom, String domain, String root, String update ) {
    if (site!=null){
      editor.putString(SaveManager.url_site, site);
    }
    if (kingdom!=null){
      editor.putString(SaveManager.url_kingdom, kingdom);
    }
    if (domain!=null){
      editor.putString(SaveManager.url_domain, domain);
    }
    if (root!=null){
      editor.putString(SaveManager.url_root, root);
    }
    if (update!=null){
      editor.putString(SaveManager.url_update, update);
    }
    editor.apply();
  }

  public void save_status_update_version(Boolean HasNewVersion) {
    editor.putBoolean(SaveManager.updateVersion, HasNewVersion);
    editor.apply();
  }

  public void save_status_deprecated_version(int versionIsDeprecated) {
    editor.putInt(SaveManager.deprecatedVersion, versionIsDeprecated);
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

    hashMap.put(url_site, sharedPreferences.getString(url_site, "https://salamquran.com" ));
    hashMap.put(url_kingdom, sharedPreferences.getString(url_kingdom, "https://salamquran.com/en" ));
    hashMap.put(url_domain, sharedPreferences.getString(url_domain, "salamquran.com" ));
    hashMap.put(url_root, sharedPreferences.getString(url_root, "salamquran" ));
    hashMap.put(url_update, sharedPreferences.getString(url_update, "https://salamquran.com/app/update" ));

    return hashMap;
  }

  public Map<String, Integer> getIntValue() {
    HashMap<String, Integer> hashMap = new HashMap<>();
    hashMap.put(splash, sharedPreferences.getInt(splash, 0 ));
    hashMap.put(deprecatedVersion, sharedPreferences.getInt(deprecatedVersion, 0));

    return hashMap;
  }

  public Map<String,Boolean> getBoolValue() {
    HashMap<String, Boolean> hashMap = new HashMap<>();
    hashMap.put(unZipFileReq, sharedPreferences.getBoolean(unZipFileReq, false));
    hashMap.put(updateVersion, sharedPreferences.getBoolean(updateVersion, false));
    return hashMap;
  }


}


