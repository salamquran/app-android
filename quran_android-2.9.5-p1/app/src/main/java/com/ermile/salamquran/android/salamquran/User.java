package com.ermile.salamquran.android.salamquran;

import android.content.Context;

public class User {

  public static String getApikey(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.apikey);
  }
  public static String getUserCode(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.apikey);
  }
  public static String getZonId(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.apikey);
  }
  public static String getMobile(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.apikey);
  }

}
