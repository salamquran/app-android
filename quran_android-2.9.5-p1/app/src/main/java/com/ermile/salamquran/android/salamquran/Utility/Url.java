package com.ermile.salamquran.android.salamquran.Utility;

import android.content.Context;

import com.ermile.salamquran.android.salamquran.Utility.SaveManager;

public class Url {
  public static String site = "https://salamquran.com";
  public static String api = site+"/api/v6";

  public static String getLocal(Context context){
    return SaveManager.get(context).getstring_appINFO().get(SaveManager.local);
  }
//Learn
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
//Mag
  public static String getMagList(Context context, int limit){
    String lms_level = "/posts?limit="+limit;
    return getLocal(context) + lms_level;
  }
//Token & Login
  public static String getToken(){
    String token = "/token";
    return api + token;
  }
  public static String getUserAdd(Context context){
    String user_add = "/android/user/add";
    return getLocal(context) + user_add;
  }
// Aya & Page Day
  public static String getAyaDay(Context context){
    String aya_day = "/aya/day";
    return getLocal(context) + aya_day;
  }
  public static String getPageDay(Context context){
    String page_day = "/page/day";
    return getLocal(context) + page_day;
  }
//  Notification
  public static String getSmile(Context context){
    String smile = "/smile";
    return getLocal(context) + smile;
  }
  public static String getNotif(Context context){
    String notif = "/notif";
    return getLocal(context) + notif;
  }


}
