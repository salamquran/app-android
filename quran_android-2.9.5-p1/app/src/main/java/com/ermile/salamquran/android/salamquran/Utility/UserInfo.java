package com.ermile.salamquran.android.salamquran.Utility;

import android.content.Context;

import com.ermile.salamquran.android.BuildConfig;

public class UserInfo {
  public static String versionName = BuildConfig.VERSION_NAME;
  public static int versionCode = BuildConfig.VERSION_CODE;

  public static String getApikey(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.apikey);
  }
  public static String getUserCode(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.userCode);
  }
  public static String getZonId(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.zonId);
  }
  public static String getMobile(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.mobile);
  }

  public static Boolean userIdAdded(Context context){
    return getApikey(context) != null || getUserCode(context) != null || getZonId(context) != null;
  }

}
