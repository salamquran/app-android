package com.ermile.salamquran.android.salamquran.Utility;

import android.content.Context;

public class Url {
  public static String site = "https://salamquran.com";
  public static String api = site+"/api/v6";
  public static String apiV6 = "/api/v6";

  public static String getLocal(Context context){
    return site + "/"
            + SaveManager.get(context).getstring_appINFO().get(SaveManager.appLanguage)
            + apiV6;
  }
//Learn
  public static String getLmsGroup(Context context){
    return getLocal(context) + "/lms/group";
  }
  public static String getLmsLevelList(Context context, String id){
    return getLocal(context) + "/lms/levellist?id="+id;
  }
  public static String getLmsLevelInfo(Context context, String id){
    return getLocal(context) + "/lms/level?id="+id;
  }
//Mag
  public static String getMagList(Context context, int limit){
    return getLocal(context) + "/posts?limit="+limit ;
  }
//Token & Login
  public static String getToken(){
    return api + "/token";
  }
  public static String getUserAdd(Context context){
    return getLocal(context) + "/android/user/add";
  }
//  Get Android Detail
  public static String getAndroidDetail(Context context){
    return getLocal(context) + "/app";
  }
  //  Get Language List
  public static String getLanguageList(Context context){
    return getLocal(context) + "/language";
  }
// Aya & Page Day
  public static String getAyaDay(Context context){
    return getLocal(context) + "/aya/day";
  }
  public static String getPageDay(Context context){
    return getLocal(context) + "/page/day";
  }
//  Notification
  public static String getSmile(Context context){
    return getLocal(context) + "/smile";
  }
  public static String getNotif(Context context){
    return getLocal(context) + "/notif";
  }


}
