package com.ermile.salamquran.android.salamquran.Utility;

import android.content.Context;

import com.ermile.salamquran.android.salamquran.Utility.SaveManager;

public class Url {
  public static String site = "https://salamquran.com";
  public static String api = site+"/api/v6";

  public static String getLocal(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.local);
  }

  public static String getLmsGroup(Context context){
    String lms_group = "/lms/group";
    return getLocal(context) + lms_group;
  }
  public static String getLmsLevelList(Context context, String id){
    String lms_level_list = "/lms/levellist?id="+id;
    return getLocal(context) + lms_level_list;
  }
  public static String getLmsLevelInfo(Context context, String id){
    String lms_level = "/lms/level?id="+id;
    return getLocal(context) + lms_level;
  }

  public static String getMagList(Context context, int limit){
    String lms_level = "/posts?limit="+limit;
    return getLocal(context) + lms_level;
  }

  public static String getToken(){
    String token = "/token";
    return api + token;
  }
  public static String getUserAdd(Context context){
    String user_add = "/android/user/add";
    return getLocal(context) + user_add;
  }
}
